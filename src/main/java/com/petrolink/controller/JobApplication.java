package com.petrolink.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petrolink.dao.ProfileDao;
import com.petrolink.file.service.FileUploadRunner;
import com.petrolink.model.Profile;

@CrossOrigin
@RestController
@RequestMapping("/petrolink")
public class JobApplication {

	@Value("${filePath}")
	private String filePath;

	@Autowired
	private ProfileDao profileDao;

	public JobApplication() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/profile")
	public Profile profle(@RequestParam("file") MultipartFile file, @RequestParam("profile") String data) {

		Profile profile = null;

		try {
			profile = new ObjectMapper().readValue(data, Profile.class);

			profileDao.save(profile);

			String careerId = String.valueOf(profile.getCareerId());
			String profileId = String.valueOf(profile.getId());
			String fileName = file.getOriginalFilename();

			String extendedFileName = "P" + "_" + careerId + "_" + profileId + "_" + fileName;

			new Thread(new FileUploadRunner(file.getInputStream(), filePath, extendedFileName)).start();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Exception occured while job posting ", e);
		}

		return profile;
	}

	@GetMapping("/profile")
	public List<Profile> getProfiles() {
		return (List<Profile>) profileDao.findAll();
	}

	@GetMapping("/profile/career/{careerId}")
	public List<Profile> get(@PathVariable int careerId) {

		List<Profile> profiles = null;
		try {
			profiles = profileDao.findProfileByCareerId(careerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Job Posting not found for the careerId " + careerId, e);
		}

		return profiles;
	}

	@RequestMapping("/profile/download/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		// fileName expected in P_2_7_ in this careerId is 2 and profileId is 7
		try {
			File dir = new File(filePath);

			File[] matches = dir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {

					return name.startsWith(fileName);
				}
			});

			File file = null;

			if (matches.length > 0) {
				file = matches[0];
			}

			if (file == null) {
				throw new RuntimeException("File does not found with starting with that file name  " + fileName);
			}

			if (file.exists()) {

				String fullFileName = file.getName();
				String originalFileName = fullFileName.replace(fileName, "");

				System.out.println("fileName ::" + fileName);
				System.out.println("originalFileName ::" + originalFileName);

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {
					// unknown mimetype so set the mimetype to application/octet-stream
					mimeType = "application/octet-stream";
				}

				response.setContentType(mimeType);

				/**
				 * Here we have mentioned it to show inline
				 */
				// response.setHeader("Content-Disposition", String.format("inline; filename=\""
				// + file.getName() + "\""));

				// Here we have mentioned it to show as attachment
				response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
				response.setHeader("Content-Disposition",
						String.format("attachment; filename=\"" + originalFileName + "\""));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				FileCopyUtils.copy(inputStream, response.getOutputStream());

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Exception occured while downloading document ", e);
		}
	}

}
