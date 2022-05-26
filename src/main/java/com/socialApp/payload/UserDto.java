package com.socialApp.payload;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.socialApp.entity.UserImages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserDto {
	
	private int id;
	@NotBlank(message = "Please enter some input!!")
	private String firstName;
	@NotBlank(message = "Please enter some input!!")
	private String lastName;
	@NotBlank(message = "Please enter some input!!")
	private String address;
	@NotBlank(message = "Username cannot be empty!!")
	private String username;
	@NotBlank(message = "Please enter some input!!")
	@Size(min = 8, max = 12,message = "Password should be between 8 to 12 digits!!")
	private String password;
	@Email(message = "Please enter some input!!")
	private String email;
	private String gender;
	private List<UserImages> images;
	
	private Set<RolesDto> userRoles=new HashSet<>();

}
