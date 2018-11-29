package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Cannot Found User") //404
public class UserNotfoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UserNotfoundException(String username) {
		super("UserNotfoundException with username=" + username);
	}
}
