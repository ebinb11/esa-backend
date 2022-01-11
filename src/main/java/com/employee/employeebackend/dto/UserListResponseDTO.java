package com.employee.employeebackend.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserListResponseDTO {
	
	private Integer totalPages;

	private Integer currentPages;

	private Long total;
	
	private List<UserResponseDTO> userList;

}
