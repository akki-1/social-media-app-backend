package com.socialApp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.socialApp.payload.ServerResponse;

// this is global exception handling
@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ServerResponse> userNotFoundException(UserNotFoundException userEx) {

		String msg = userEx.getMessage();
		ServerResponse sr = new ServerResponse(msg, false);
		return new ResponseEntity<ServerResponse>(sr, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodNotValidHandler(MethodArgumentNotValidException exp) {
		Map<String, String> response = new HashMap<>();
		exp.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomBadCredential.class)
	public ResponseEntity<ServerResponse> customBadCredentialHandler(CustomBadCredential userEx) {

		String msg = userEx.getMessage();
		ServerResponse sr = new ServerResponse(msg, false);
		return new ResponseEntity<ServerResponse>(sr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomUsernameNotFoundException.class)
	public ResponseEntity<ServerResponse> usernameNotFoundException(CustomUsernameNotFoundException userEx) {

		String msg = userEx.getMessage();
		ServerResponse sr = new ServerResponse(msg, false);
		return new ResponseEntity<ServerResponse>(sr, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ServerResponse> userNotHaveAccess() {

		
		String msg = "You don't have Authority to acess this API";
		ServerResponse sr = new ServerResponse(msg, false);
		return new ResponseEntity<ServerResponse>(sr, HttpStatus.UNAUTHORIZED);
	}
}
