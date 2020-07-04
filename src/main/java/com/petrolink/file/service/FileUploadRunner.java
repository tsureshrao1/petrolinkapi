package com.petrolink.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadRunner implements Runnable {

	private InputStream inputStream;
	private String filePath;
	private String fileName;
	

	public FileUploadRunner() {
		// TODO Auto-generated constructor stub
	}

	public FileUploadRunner(InputStream inputStream, String filePath, String fileName) {
		super();
		this.inputStream = inputStream;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	@Override
	public void run() {

		try {
			Path root = Paths.get(filePath);
			Files.copy(inputStream, root.resolve(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
