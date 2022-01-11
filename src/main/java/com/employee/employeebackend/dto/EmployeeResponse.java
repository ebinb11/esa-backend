package com.employee.employeebackend.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeResponse {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNo;
	private Date dob;
}
