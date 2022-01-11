package com.employee.employeebackend.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeRequest {

	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNo;
	private Date dob;
}
