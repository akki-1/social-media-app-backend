package com.socialApp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RolesDto {
	
	private int roleId;
	private String RollName;
	
	public RolesDto( String rollName) {
		super();
		RollName = rollName;
	}

}
