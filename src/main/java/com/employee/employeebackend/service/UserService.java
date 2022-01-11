package com.employee.employeebackend.service;

import org.springframework.data.domain.Sort.Direction;

import com.employee.employeebackend.dto.StatusResponse;
import com.employee.employeebackend.dto.UserListResponseDTO;
import com.employee.employeebackend.dto.UserRequestDTO;
import com.employee.employeebackend.dto.UserResponseDTO;
import com.employee.employeebackend.entity.User;

public interface UserService {

	User findUserByEmailAddress(final String userName); 
	
	UserListResponseDTO getUserList(Integer page, Integer size, String search, Direction sort, Long id);

	UserResponseDTO userEntry(UserRequestDTO request);

	UserResponseDTO userGetById(Long id);

	StatusResponse deleteUser(Long id);

}
