package com.employee.employeebackend.service;

import com.employee.employeebackend.dto.AuthRequestDTO;
import com.employee.employeebackend.dto.AuthResponseDTO;

public interface AuthenticationService {

	AuthResponseDTO loginCheck(AuthRequestDTO authRequestDTO);
}
