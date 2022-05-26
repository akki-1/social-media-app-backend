package com.socialApp.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class JwtRequest {

	@NotBlank(message = "Username cannot be empty!!")
	String username;
	@Size(min = 8, max = 12, message = "Password should be between 8 to 12 digits!!")
	String password;

	public JwtRequest() {
		super();
	}

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtReq [username=" + username + ", password=" + password + "]";
	}

}
