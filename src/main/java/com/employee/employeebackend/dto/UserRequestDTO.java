package com.employee.employeebackend.dto;

import lombok.Data;

@Data
public class UserRequestDTO {

	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String password;
}
