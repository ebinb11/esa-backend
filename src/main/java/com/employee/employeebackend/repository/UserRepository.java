package com.employee.employeebackend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.employeebackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM `user` WHERE `first_name` LIKE ?1% OR `last_name` LIKE ?1% OR `email` LIKE ?1% AND `is_deleted` = ('false')", nativeQuery = true)
	Page<User> findBySearch(String search, Pageable pageable);

	@Query("FROM User as u WHERE u.id = ?1 AND u.isDeleted = 'false' ")
	Page<User> findByIdAndDeletedFalse(Long id, Pageable pageable);

	@Query("FROM User as u WHERE u.email = ?1 AND u.isDeleted = 'false' ")
	Optional<User> findByEmailIdAndDeletedfalse(String emailId);

	@Query("FROM User as u WHERE u.id =?1 AND u.isDeleted ='false' ")
	Optional<User> findByIdAndDeletedFalsed(Long id);
	
	@Query("FROM User as u WHERE u.email = ?1 AND u.isDeleted = 'false' ")
	User findByEmailIdAndDeletedfalses(String emailId);

	Optional<User> findByIdAndIsDeletedFalse(Long id);
}
