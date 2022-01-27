package com.employee.employeebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadDataException(String message) {
		super(message);
	}

	public BadDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadDataException(Throwable cause) {
		super(cause);
	}

}
