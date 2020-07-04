package com.petrolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

}
