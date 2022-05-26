package com.socialApp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialApp.entity.Roles;
import com.socialApp.entity.User;
import com.socialApp.exception.UserNotFoundException;
import com.socialApp.payload.UserDto;
import com.socialApp.repo.RoleRepo;
import com.socialApp.repo.UserRepo;
import com.socialApp.serviceInterfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepo ur;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	BCryptPasswordEncoder bcp;

	@Autowired
	private RoleRepo rr;



	@Override
	public UserDto createUserDto(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		user.setPassword(this.bcp.encode(user.getPassword()));
		Roles role = this.rr.getById(222);
		user.getUserRoles().add(role);
		User savedUser = this.ur.save(user);
		
		return this.userToDto(savedUser);

	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.ur.findById(id).orElseThrow(() -> new UserNotFoundException("User", "id", id));
		return this.userToDto(user);
	}

	@Override
	public void deleteUser(Integer id) {
		User user = this.ur.findById(id).orElseThrow(() -> new UserNotFoundException("User", "id", id));
		this.ur.delete(user);

	}

	
	// mapping userDto to user with the help of ModelMapper
	// can write without model mapper too but that will increase lines of codes 
	private User dtoToUser(UserDto udo) {

		User user = this.mapper.map(udo, User.class);

		return user;
	}
	// mapping user to userDto with the help of ModelMapper
	public UserDto userToDto(User user) {

		UserDto udo = this.mapper.map(user, UserDto.class);

		return udo;
	}

}
