package com.employee.employeebackend.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.employeebackend.dto.StatusResponse;
import com.employee.employeebackend.dto.UserListResponseDTO;
import com.employee.employeebackend.dto.UserRequestDTO;
import com.employee.employeebackend.dto.UserResponseDTO;
import com.employee.employeebackend.entity.User;
import com.employee.employeebackend.exception.BadDataException;
import com.employee.employeebackend.repository.UserRepository;
import com.employee.employeebackend.service.UserService;
import com.google.gson.Gson;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findUserByEmailAddress(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user
				.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList()));
	}

	@Override
	public User findUserByEmailAddress(String userName) throws UsernameNotFoundException {
		if (userName != null && !userName.isEmpty()) {
			User user = userRepository.findByEmailIdAndDeletedfalses(userName);
			return user;
		} else {
			throw new BadDataException("Email or phone number mismatch.");
		}
	}

	@Override
	public UserListResponseDTO getUserList(Integer page, Integer size, String search, Direction sort, Long id) {
		Page<User> user;
		List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
		UserListResponseDTO userListResponseDTO = new UserListResponseDTO();
		PageRequest pageRequest = PageRequest.of(page, size, sort, "firstName");
		PageRequest pageRequestQuery = PageRequest.of(page, size, sort, "first_name");

		try {
			if (search != null && !search.isEmpty()) {
				user = userRepository.findBySearch(search, pageRequestQuery);
			} else if (id != null && id != 0) {
				user = userRepository.findByIdAndDeletedFalse(id, pageRequest);
			} else {
				user = userRepository.findAll(pageRequest);
			}

			if (user != null && user.getContent().size() > 0) {
				user.getContent().forEach(userObj -> {
					Gson gson = new Gson();
					UserResponseDTO userResponseDTO = gson.fromJson(gson.toJson(userObj), UserResponseDTO.class);
					userResponseDTOList.add(userResponseDTO);
				});
			}

			if (userResponseDTOList.size() > 0) {
				userListResponseDTO.setCurrentPages(page);
				userListResponseDTO.setTotal(user.getTotalElements());
				userListResponseDTO.setTotalPages(user.getTotalPages());
				userListResponseDTO.setUserList(userResponseDTOList);
			} else {
				userListResponseDTO.setCurrentPages(0);
				userListResponseDTO.setTotalPages(0);
				userListResponseDTO.setTotal(0L);
				userListResponseDTO.setUserList(userResponseDTOList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userListResponseDTO;
	}

	@Override
	public UserResponseDTO userEntry(UserRequestDTO request) {
		try {
			if (request.getEmail() != null) {
				Optional<User> userGet = userRepository.findByEmailIdAndDeletedfalse(request.getEmail());
				if (userGet.isPresent()) {
					throw new BadDataException("Given email id" + request.getEmail() + "exist already!");
				}
			}
			Gson gson = new Gson();
			User userSet = gson.fromJson(gson.toJson(request), User.class);
			userSet.setCreatedBy("Admin Ebin");
			userSet.setCreatedOn(new Date());
			userSet.setPassword(passwordEncoder.encode(request.getPassword()));
			User userSaveInDB = userRepository.save(userSet);
			if (userSaveInDB == null) {
				throw new BadDataException("Something went wrong when tried to enter data");
			}
			UserResponseDTO response = gson.fromJson(gson.toJson(userSaveInDB), UserResponseDTO.class);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserResponseDTO userGetById(Long id) {
		Optional<User> userGet = userRepository.findByIdAndDeletedFalsed(id);
		if (!userGet.isPresent()) {
//			throw new BadDataException("User not found !");
			return null;
		}
		Gson gson = new Gson();
		UserResponseDTO response = gson.fromJson(gson.toJson(userGet.get()), UserResponseDTO.class);
		return response;
	}

	@Override
	public StatusResponse deleteUser(Long id) {
		StatusResponse response = new StatusResponse();
		Optional<User> userGet = userRepository.findByIdAndDeletedFalsed(id);
		if (!userGet.isPresent()) {
			throw new BadDataException("User not found !");
		}
		userGet.get().setDeleted(true);
		User userSaveInDB = userRepository.save(userGet.get());
		if (userSaveInDB != null) {
			response.setMessages("User deleted successfully !");
		} else {
			response.setMessages("User not deleted successfully , try again later !");
		}
		return response;
	}
}
