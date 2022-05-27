package com.socialApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialApp.payload.JwtRequest;
import com.socialApp.payload.JwtResponse;
import com.socialApp.payload.ServerResponse;
import com.socialApp.payload.UserDto;
import com.socialApp.service.JwtService;
import com.socialApp.service.UserService;

// this is our main app controller, where we are performing our crud operations
@RestController
@RequestMapping("/app")
public class AppController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	// this api is open for all where new user can signup by providing necessary details
	// we are using @Valid annotation before UserDto object , this will take care of invalid fields like 
	// if user left blank in username section or password then this will return proper message to user.
	@PostMapping("/signup")
	public ResponseEntity<UserDto> signUp(@RequestBody @Valid UserDto udo) {
		
		UserDto userDto = this.userService.createUserDto(udo);

		return new ResponseEntity<>(userDto, HttpStatus.CREATED);
	}

	// this api is also open for all and user can login here
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody @Valid JwtRequest req) {

		return jwtService.generateToken(req);
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<ServerResponse> removeUser(@PathVariable Integer id) {
		this.userService.deleteUser(id);

		return new ResponseEntity<ServerResponse>(new ServerResponse("User removed successfully", true), HttpStatus.OK);
	}

	// this api will return user details
	@GetMapping("/get-user/{id}")
	public UserDto getUser(@PathVariable Integer id) {
		
		return this.userService.getUserById(id);
	}
}
