package com.socialApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.socialApp.entity.Following;
import com.socialApp.entity.User;
import com.socialApp.repo.UserRepo;
import com.socialApp.serviceInterfaces.FollowingInt;

@Service
public class FollowingService implements FollowingInt {

	@Autowired
	UserRepo ur;

	@Override
	public ResponseEntity<String> addUserUFollow(Following following, Integer userId) {
		ArrayList<Following> al = new ArrayList<Following>();
		al.add(following);
		User user = this.ur.getById(userId);
		following.setUser(user);
		user.setFollowing(al);
		this.ur.save(user);
		return new ResponseEntity<String>("Added", HttpStatus.ACCEPTED);
	}

	@Override
	public List<Following> getAll(Integer id) {
		User user = this.ur.getById(id);
		return user.getFollowing();
	}

}
