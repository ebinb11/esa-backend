package com.employee.employeebackend.exception;

public class BadCredentialException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadCredentialException(String message) {
		super(message);
	}
}
