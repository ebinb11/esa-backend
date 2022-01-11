package com.employee.employeebackend.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.employee.employeebackend.dto.EmployeeListResponse;
import com.employee.employeebackend.dto.EmployeeRequest;
import com.employee.employeebackend.dto.EmployeeResponse;
import com.employee.employeebackend.dto.StatusResponse;
import com.employee.employeebackend.entity.Employee;
import com.employee.employeebackend.exception.BadDataException;


public interface EmployeeService {

	EmployeeListResponse getEmployeeListPage(int page, int size, String search, Sort.Direction sort, Long id);

	EmployeeResponse employeeEntry(EmployeeRequest request) throws BadDataException;

	EmployeeResponse employeeGet(Long id) throws BadDataException;

	EmployeeResponse employeeUpdate(Long id, EmployeeRequest request) throws BadDataException;

	StatusResponse employeeDelete(Long id);

	List<Employee> getEmployeeList();
}
