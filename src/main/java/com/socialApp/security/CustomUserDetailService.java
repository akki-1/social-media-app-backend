package com.socialApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialApp.entity.User;
import com.socialApp.exception.CustomUsernameNotFoundException;
import com.socialApp.repo.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo up;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.up.findByUsername(username);
		if (user == null) {

			throw new CustomUsernameNotFoundException("Username not found !!");
		}
		return user;
	}

}
