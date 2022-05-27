
package com.socialApp.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldValue;
// this is our custom exception
	public UserNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
