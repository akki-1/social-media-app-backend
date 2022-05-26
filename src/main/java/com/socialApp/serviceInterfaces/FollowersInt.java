package com.socialApp.serviceInterfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.socialApp.entity.Followers;

public interface FollowersInt {
	
	ResponseEntity<String> addFollower(Followers follower, Integer userId);
	public List<Followers> getAll(Integer id);

}
