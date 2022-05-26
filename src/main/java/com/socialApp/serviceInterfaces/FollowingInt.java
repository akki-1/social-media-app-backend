package com.socialApp.serviceInterfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.socialApp.entity.Following;

public interface FollowingInt {
	
	ResponseEntity<String> addUserUFollow(Following following, Integer userId);
	public List<Following> getAll(Integer id);

}
