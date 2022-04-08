package com.employee.employeebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeebackend.dto.AuthRequestDTO;
import com.employee.employeebackend.dto.AuthResponseDTO;
import com.employee.employeebackend.exception.BadCredentialException;
import com.employee.employeebackend.service.AuthenticationService;
import com.employee.employeebackend.utils.AppResponse;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(value = "/authenticate")
	public AppResponse<AuthResponseDTO> authenticatea(@RequestBody AuthRequestDTO authRequestDTO) throws BadCredentialException {

		AuthResponseDTO authResponseDTO = authenticationService.loginCheck(authRequestDTO);
		if (authResponseDTO != null) {
			return AppResponse.<AuthResponseDTO>builder()
					.data(authResponseDTO)
					.message("Successfully authenticated user.")
					.success(true)
					.build();
		}
		return AppResponse.<AuthResponseDTO>builder()
				.success(false)
				.message("Authentication error, please check provided email or password!")
				.build();
	}
}
