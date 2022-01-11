package com.employee.employeebackend.service.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.employee.employeebackend.dto.EmployeeListResponse;
import com.employee.employeebackend.dto.EmployeeRequest;
import com.employee.employeebackend.dto.EmployeeResponse;
import com.employee.employeebackend.dto.StatusResponse;
import com.employee.employeebackend.entity.Employee;
import com.employee.employeebackend.exception.BadDataException;
import com.employee.employeebackend.repository.EmployeeRepository;
import com.employee.employeebackend.service.EmployeeService;
import com.employee.employeebackend.service.MailService;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	MailService mailService;
	
	@Override
	public List<Employee> getEmployeeList() {
		return employeeRepository.findAllByIsDeletedFalse();
	}

	@Override
	public EmployeeListResponse getEmployeeListPage(int page, int size, String search, Direction sort, Long id) {
		Page<Employee> employee;
		List<EmployeeResponse> employeeResponsesList = new ArrayList<>();
		EmployeeListResponse employeeListResponse = new EmployeeListResponse();
		PageRequest pageRequest = PageRequest.of(page, size, sort, "firstName");
		PageRequest pageRequestQuery = PageRequest.of(page, size, sort, "first_name");

		if (search != null && !search.isEmpty()) {
			employee = employeeRepository.findBySearch(search, pageRequestQuery);
		} else if (id != null && id != 0) {
			employee = employeeRepository.findById(id, pageRequestQuery);
		} else {
			employee = employeeRepository.findAll(pageRequest);
		}

		if (employee != null && employee.getContent().size() > 0) {
			employee.getContent().forEach(employeeObj -> {
				EmployeeResponse employeeResponse = response(employeeObj);
				if (employeeResponse != null) {
					employeeResponsesList.add(employeeResponse);
				}
			});

			if (employeeResponsesList.size() > 0) {
				employeeListResponse.setCurrentPages(page);
				employeeListResponse.setTotalPages(employee.getTotalPages());
				employeeListResponse.setTotal(employee.getTotalElements());
				employeeListResponse.setEmployeeList(employeeResponsesList);
			} else {
				employeeListResponse.setCurrentPages(0);
				employeeListResponse.setTotalPages(0);
				employeeListResponse.setTotal(0L);
				employeeListResponse.setEmployeeList(employeeResponsesList);
			}
		}
		return employeeListResponse;
	}
	@Override
	@Transactional
	public EmployeeResponse employeeEntry(EmployeeRequest request) throws BadDataException {

		Date currentSqlDate = new Date(System.currentTimeMillis());
		Optional<Employee> employeeGet = employeeRepository.findByEmailIdAndIsDeletedFalse(request.getEmailId());
		if (employeeGet.isPresent()) {
			throw new BadDataException("Given email id" + request.getEmailId() + "exist already!");
		}
		Employee employee = new Employee();
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setDob(currentSqlDate);
		employee.setEmailId(request.getEmailId());
		employee.setPhoneNo(request.getPhoneNo());
		employee.setDeleted(false);
		Employee saveToDb = employeeRepository.save(employee);
		mailService.esafNotification(saveToDb);
		return response(saveToDb);
	}
	
	@Override
	public EmployeeResponse employeeGet(Long id) throws BadDataException {

		if (id == null || id == 0) {
			throw new BadDataException("id should not be empty!");
		}

		Optional<Employee> employeeGet = employeeRepository.findByIdAndIsDeletedFalse(id);
		if (!employeeGet.isPresent()) {
			throw new BadDataException("No data found by given" + id + "id");
		}
		return response(employeeGet.get());
	}
	
	@Override
	public EmployeeResponse employeeUpdate(Long id, EmployeeRequest request) throws BadDataException {

		if (id == null || id == 0) {
			throw new BadDataException("id should not be empty!");
		}
		try {
			Optional<Employee> employeeGet = employeeRepository.findByIdAndIsDeletedFalse(id);
			if (!employeeGet.isPresent()) {
				throw new BadDataException("No data found by given" + id + "id");
			}
			employeeGet.get().setFirstName(request.getFirstName());
			employeeGet.get().setLastName(request.getLastName());
			employeeGet.get().setPhoneNo(request.getPhoneNo());
//			employeeGet.get().setDob(request.getDob());
			employeeGet.get().setEmailId(request.getEmailId());
			Employee saveToDb = employeeRepository.save(employeeGet.get());
			if (saveToDb == null) {
				throw new BadDataException("Something went wrong, try again after some time!");
			}
			return response(saveToDb);
		} catch (Exception e) {
			throw new BadDataException(e.toString());
		}
	}

	@Override
	public StatusResponse employeeDelete(Long id) {

		StatusResponse response = new StatusResponse();
		if (id == null || id == 0) {
			throw new BadDataException("id should not be empty!");
		}

		Optional<Employee> employeeGet = employeeRepository.findByIdAndIsDeletedFalse(id);
		if (!employeeGet.isPresent()) {
			throw new BadDataException("No data found by given" + id + "id");

		}
		employeeGet.get().setDeleted(true);
		Employee saveToDb = employeeRepository.save(employeeGet.get());

		if (saveToDb == null) {
			throw new BadDataException("Something went wrong, try again after some time!");
		}
		response.setMessages("Given profile is deleted successfully!");
		return response;
	}

	public EmployeeResponse response(Employee employee) {

		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setDob(employee.getDob());
		response.setEmailId(employee.getEmailId());
		response.setPhoneNo(employee.getPhoneNo());
		return response;
	}


}
