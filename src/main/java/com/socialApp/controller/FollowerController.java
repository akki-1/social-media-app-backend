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

import com.socialApp.entity.Followers;
import com.socialApp.service.FollowersService;

@RestController
@RequestMapping("user/")
public class FollowerController {

	@Autowired
	FollowersService fservice;

	// this is just to add followers so we can get list of followers in next API
	@PostMapping("add-follower/{userId}")
	public ResponseEntity<String> addFollower(@RequestBody Followers follower, @PathVariable Integer userId) {

		return fservice.addFollower(follower, userId);

	}

	// this will return list of followers of user by providing user id
	@GetMapping("get-all/{id}")
	public List<Followers> getAll(@PathVariable Integer id) {

		return this.fservice.getAll(id);
	}

}
