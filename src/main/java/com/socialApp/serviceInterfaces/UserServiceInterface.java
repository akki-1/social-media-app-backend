package com.socialApp.serviceInterfaces;

import org.springframework.http.ResponseEntity;

import com.socialApp.payload.JwtRequest;
import com.socialApp.payload.JwtResponse;
import com.socialApp.payload.UserDto;

public interface UserServiceInterface {
	UserDto createUserDto(UserDto userDto);
	UserDto getUserById(Integer userID);
	void deleteUser(Integer id);
//	ResponseEntity<JwtResponse> createToken(JwtRequest req) throws Exception;

}
