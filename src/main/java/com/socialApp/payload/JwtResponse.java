package com.socialApp.payload;



public class JwtResponse {
	private String JwtToken;

	public JwtResponse() {
		super();
	}

	public JwtResponse(String jwtToken) {
		super();
		JwtToken = jwtToken;
	}

	public String getJwtToken() {
		return JwtToken;
	}

	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}

}
