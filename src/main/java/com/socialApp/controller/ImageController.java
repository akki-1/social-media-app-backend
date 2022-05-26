package com.socialApp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.socialApp.entity.User;
import com.socialApp.entity.UserImages;
import com.socialApp.repo.UserRepo;
import com.socialApp.service.ImageService;

// this entity class for images where user can post image and get image of his account
@RestController
@RequestMapping("user/image/")
public class ImageController {

	@Autowired
	ImageService iService;

	@Autowired
	private UserRepo ur;

	// this value is from application.properties,
	// u can change path anytime in application.properties
	@Value("${location.image}")
	private String path;

	// this will save image to the mention path
	@PostMapping("upload/{userId}")
	public ResponseEntity<String> imagePost(@RequestParam("image") MultipartFile image, @PathVariable Integer userId)
			throws IOException {
		ArrayList<UserImages> al = new ArrayList<UserImages>();
		String imageName = this.iService.uploadImage(this.path, image);
		User user = this.ur.getById(userId);
		UserImages uImage = new UserImages();
		uImage.setImageName(imageName);
		uImage.setSize(image.getSize());
		uImage.setUser(user);
		al.add(uImage);
		user.setImages(al);
		this.ur.save(user);

		return new ResponseEntity<String>("uploaded successfully", HttpStatus.OK);

	}

	// this will return image to client
	@GetMapping("get/{userId}")
	public void getImage(@PathVariable Integer userId, HttpServletResponse response) throws IOException {
		User user = this.ur.getById(userId);
		for (UserImages image : user.getImages()) {
			System.out.println(image.getImageName());
			InputStream image1 = this.iService.getImage(this.path, image.getImageName());
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(image1, response.getOutputStream());
		}

	}

}
