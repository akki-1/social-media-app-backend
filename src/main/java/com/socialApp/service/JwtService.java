package com.socialApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.socialApp.exception.CustomBadCredential;
import com.socialApp.payload.JwtRequest;
import com.socialApp.payload.JwtResponse;
import com.socialApp.security.CustomUserDetailService;
import com.socialApp.security.JwtUtils;

@Service
public class JwtService {

	@Autowired
	private JwtUtils utils;

	@Autowired
	private CustomUserDetailService uds;

	@Autowired
	AuthenticationManager authenticationManager;

	public ResponseEntity<JwtResponse> generateToken(JwtRequest req) {

		UserDetails user = this.uds.loadUserByUsername(req.getUsername());
		this.authenticate(req.getUsername(), req.getPassword());
		String token = this.utils.generateToken(user);
		JwtResponse response = new JwtResponse();
		response.setJwtToken(token);
		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {

		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(upat);
		} catch (Exception e) {
			throw new CustomBadCredential("Invalid Password ,try with correct password!!");
		}

	}

}
