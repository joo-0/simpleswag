package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="path variable and request body doesn't match") //400
public class InvalidRequestbodyException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidRequestbodyException() {
		super("InvalidRequestbodyException");
	}
}
