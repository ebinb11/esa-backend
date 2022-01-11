package com.employee.employeebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.employeebackend.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmailIdAndIsDeletedFalse(String emailId);

	Optional<Employee> findByIdAndIsDeletedFalse(Long id);

	List<Employee> findAllByIsDeletedFalse();

	@Query(value = "SELECT * FROM `employee` WHERE `first_name` LIKE ?1% OR `last_name` LIKE ?1% OR `email_id` LIKE ?1% AND `is_deleted` = ('false')", nativeQuery = true)
	Page<Employee> findBySearch(String search, Pageable pageable);

	@Query(value = "SELECT * FROM `employee` WHERE `id` = ?1", nativeQuery = true)
	Page<Employee> findById(Long id, PageRequest pageRequestQuery);
	
	Optional<Employee> findByEmailId(String emailId);

}
