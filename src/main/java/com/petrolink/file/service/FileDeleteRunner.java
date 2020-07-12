package com.petrolink.file.service;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.util.List;

import com.petrolink.dao.ProfileDao;
import com.petrolink.model.Profile;

public class FileDeleteRunner implements Runnable {

	private int careerId;
	private String filePath;
	private ProfileDao profileDao;

	public FileDeleteRunner(int careerId, String filePath, ProfileDao profileDao) {
		super();
		this.careerId = careerId;
		this.filePath = filePath;
		this.profileDao = profileDao;
	}

	@Override
	public void run() {

		List<Profile> profiles = null;
		try {
			profiles = profileDao.findProfileByCareerId(careerId);
			if (profiles != null) {
				for (Profile pro : profiles) {
					try {
						
						
						
						profileDao.delete(pro.getId());

						String fileName = "P_" + careerId + "_" + pro.getId() + "_";

						File dir = new File(filePath);

						File[] matches = dir.listFiles(new FilenameFilter() {
							public boolean accept(File dir, String name) {

								return name.startsWith(fileName);
							}
						});

						if (matches != null && matches.length > 0) {
							File file = matches[0];
							String fileFullName = file.getName();
							Files.deleteIfExists(file.toPath());
							System.out.println("Application deleted from DB and Server "+fileFullName);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Job Posting not found for the careerId " + careerId, e);
		}


	}

}
