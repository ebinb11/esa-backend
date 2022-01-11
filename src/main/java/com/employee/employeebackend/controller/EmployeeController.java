package com.employee.employeebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeebackend.dto.EmployeeListResponse;
import com.employee.employeebackend.dto.EmployeeRequest;
import com.employee.employeebackend.dto.EmployeeResponse;
import com.employee.employeebackend.dto.StatusResponse;
import com.employee.employeebackend.entity.Employee;
import com.employee.employeebackend.exception.BadDataException;
import com.employee.employeebackend.service.EmployeeService;
import com.employee.employeebackend.utils.AppResponse;

import lombok.Builder;

@RestController
@RequestMapping(value = "/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "getEmployeeList")
	public List<Employee> getEmployeeList() {
		List<Employee> response = employeeService.getEmployeeList();
		return response;
	}
	
	// Custom app Response example.
	@GetMapping(value = "getEmployeeListAppResponse")
	public AppResponse<List<Employee>> getEmployeeListApp() {
		List<Employee> response = employeeService.getEmployeeList();
		if (response.size() > 0) {
			return AppResponse.<List<Employee>>builder()
					.data(response)
					.message("Employee Listed successfully!")
					.success(true)
					.build();
		} 
		
		return AppResponse.<List<Employee>>builder()
				.message("No data found!")
				.success(false)
				.build();
		
	}

	@GetMapping(value = "getEmployeeListPage")
	public ResponseEntity<EmployeeListResponse> getEmployeeList(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@RequestParam(value = "sort", required = false, defaultValue = "ASC") Sort.Direction sort,
			@RequestParam(value = "id", required = false, defaultValue = "") Long id) {
		EmployeeListResponse response = employeeService.getEmployeeListPage(page, size, search, sort, id);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/employeeEntry")
	public ResponseEntity<EmployeeResponse> employeeEntry(@RequestBody EmployeeRequest request)
			throws BadDataException {
		EmployeeResponse response = employeeService.employeeEntry(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "employeeGet/{id}")
	public ResponseEntity<EmployeeResponse> employeeGet(@PathVariable Long id) throws BadDataException {
		EmployeeResponse response = employeeService.employeeGet(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "employeeUpdate/{id}")
	public ResponseEntity<EmployeeResponse> employeeUpdate(@PathVariable Long id, @RequestBody EmployeeRequest request)
			throws BadDataException {
		EmployeeResponse response = employeeService.employeeUpdate(id, request);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "employeeDelete/{id}")
	public ResponseEntity<StatusResponse> empoyeeDelete(@PathVariable Long id) {
		StatusResponse response = employeeService.employeeDelete(id);
		return ResponseEntity.ok(response);
	}
}
