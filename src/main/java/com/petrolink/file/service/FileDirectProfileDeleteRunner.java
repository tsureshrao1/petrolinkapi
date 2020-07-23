package com.petrolink.file.service;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;

import com.petrolink.dao.ProfileDao;

public class FileDirectProfileDeleteRunner implements Runnable {

	private int profileId;
	private String filePath;
	private ProfileDao profileDao;

	public FileDirectProfileDeleteRunner(int profileId, String filePath, ProfileDao profileDao) {
		super();
		this.profileId = profileId;
		this.filePath = filePath;
		this.profileDao = profileDao;
	}

	@Override
	public void run() {

		try {

			String fileName = "DP_" + profileId + "_";

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
				System.out.println("Application deleted from DB and Server " + fileFullName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
