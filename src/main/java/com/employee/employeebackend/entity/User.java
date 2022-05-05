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
	
//	@ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    @EqualsAndHashCode.Exclude
//    private Set<Role> roles = new HashSet<>();
}
