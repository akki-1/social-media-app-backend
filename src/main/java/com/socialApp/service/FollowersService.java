package com.socialApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.socialApp.entity.Followers;
import com.socialApp.entity.User;
import com.socialApp.repo.UserRepo;
import com.socialApp.serviceInterfaces.FollowersInt;

@Service
public class FollowersService implements FollowersInt {

	@Autowired
	UserRepo ur;

	@Override
	public ResponseEntity<String> addFollower(Followers follower, Integer userId) {
		ArrayList<Followers> al = new ArrayList<Followers>();
		al.add(follower);
		User user = this.ur.getById(userId);
		follower.setUser(user);
		user.setFollowers(al);
		this.ur.save(user);
		return new ResponseEntity<String>("Added", HttpStatus.ACCEPTED);
	}

	@Override
	public List<Followers> getAll(Integer id) {
		User user = this.ur.getById(id);
		return user.getFollowers();
	}

}
