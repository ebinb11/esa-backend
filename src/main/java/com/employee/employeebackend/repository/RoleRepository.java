package com.employee.employeebackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.employeebackend.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("FROM Role as r WHERE r.name= ?1")
	Optional<Role> findByName(String name);

}
