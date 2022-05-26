package com.socialApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialApp.entity.Following;
import com.socialApp.service.FollowingService;

@RestController
@RequestMapping("user/")
public class FollowingController {

	@Autowired
	FollowingService followingService;

	// this api will add the user whom our user followed in following table
	@PostMapping("add-following/{userId}")
	public ResponseEntity<String> addFollowing(@RequestBody Following following, @PathVariable Integer userId) {

		return followingService.addUserUFollow(following, userId);

	}

	// this api will return list of users to whom our user followed
	@GetMapping("get-all-following/{id}")
	public List<Following> getAll(@PathVariable Integer id) {

		return this.followingService.getAll(id);
	}

}
