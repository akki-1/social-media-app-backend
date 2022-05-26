package com.socialApp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.socialApp.serviceInterfaces.ImageServiceInt;

@Service
public class ImageService implements ImageServiceInt {

	// we can also use base64ImageEncoding here by converting image data to byte
	// array and
	// then to String and save in database

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		String name = file.getOriginalFilename();
		String imagePath = path + File.separator + name;
		Files.copy(file.getInputStream(), Paths.get(imagePath));

		return name;
	}

	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		InputStream iStream = new FileInputStream(fullPath);

		return iStream;
	}

}
