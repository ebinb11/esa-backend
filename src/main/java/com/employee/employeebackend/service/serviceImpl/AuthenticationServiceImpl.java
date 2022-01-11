package com.employee.employeebackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee.employeebackend.config.JwtTokenUtil;
import com.employee.employeebackend.dto.AuthRequestDTO;
import com.employee.employeebackend.dto.AuthResponseDTO;
import com.employee.employeebackend.entity.User;
import com.employee.employeebackend.exception.NotFoundException;
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

	@Override
	public AuthResponseDTO loginCheck(AuthRequestDTO request) throws UsernameNotFoundException {
		
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final User user = userService.findUserByEmailAddress(request.getUserName());
		if (user != null) {
			final String token = jwtTokenUtil.generateToken(user.getEmail());
			AuthResponseDTO response = new AuthResponseDTO();
			response.setToken(token);
			return response;
		}
		return null;
	}
}
