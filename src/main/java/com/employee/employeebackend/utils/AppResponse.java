package com.employee.employeebackend.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppResponse<T> {

	private T data;

	private boolean success;

	private String message;

	// default constructor.
	public AppResponse() {

	}

	public AppResponse(final T dataParam, final boolean status, final String msg) {
		this.data = dataParam;
		this.success = status;
		this.message = msg;
	}

}
