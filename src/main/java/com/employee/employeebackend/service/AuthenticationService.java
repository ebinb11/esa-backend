package com.employee.employeebackend.service;

import com.employee.employeebackend.dto.AuthRequestDTO;
import com.employee.employeebackend.dto.AuthResponseDTO;
import com.employee.employeebackend.exception.BadCredentialException;

public interface AuthenticationService {

	AuthResponseDTO loginCheck(AuthRequestDTO authRequestDTO) throws BadCredentialException;
}
