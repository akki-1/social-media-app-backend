package com.socialApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {
	// we need this bean to map our fake user details i.e coming from client side as
	// UserDto to original user;
	// Model Mapper do this for us so we don't need to write code manually to set
	// userDto fields to original user fields
	// @Bean annotation returns the object of respective class so we can autowired
	// it and can inject in whole application's class
	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

}
