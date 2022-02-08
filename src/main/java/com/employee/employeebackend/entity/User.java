package com.employee.employeebackend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String password;
	private boolean isDeleted;
	private Date createdOn;
	private String createdBy;
	private Date updatedOn;
	private String updatedBy;
	private Date deletedOn;
}
