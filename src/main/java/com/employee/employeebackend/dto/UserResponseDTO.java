package com.employee.employeebackend.dto;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserResponseDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private Date createdOn;
	private String createdBy;
	private Date updatedOn;
	private String updatedBy;
	private List<String> userRole;

}
