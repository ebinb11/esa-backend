package com.employee.employeebackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.employeebackend.dto.AuthRequestDTO;
import com.employee.employeebackend.dto.AuthResponseDTO;
import com.employee.employeebackend.entity.User;
import com.employee.employeebackend.exception.BadCredentialException;
import com.employee.employeebackend.security.JwtTokenUtil;
import com.employee.employeebackend.service.AuthenticationService;
import com.employee.employeebackend.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	PasswordEncoder PasswordEncoder;

	@Override
	public AuthResponseDTO loginCheck(AuthRequestDTO authRequestDTO) throws BadCredentialException {
		AuthResponseDTO response = new AuthResponseDTO();
		final User user = userService.findUserByEmailAddress(authRequestDTO.getUserName());
		if (user == null) {
			throw new BadCredentialException("Bad Credentials, please check provided email or password!");
		}
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDTO.getUserName(), authRequestDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (user != null) {
			if (PasswordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())) {
				final String token = jwtTokenUtil.generateToken(user.getEmail());
				response.setToken(token);
			}
		}
		return response;
	}
}
