package com.employee.employeebackend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserRequestDTO {

	@NotBlank(message = "First name shoudn't be blank")
	private String firstName;
	@NotBlank(message = "Last name shoudn't be blank")
	private String lastName;
	@Pattern(regexp = "^\\d{10}$", message = "Please provide valid phone no")
	private String phoneNo;
	@Email(message = "Please provide valid email id")
	private String email;
	private String password;
}
