package com.employee.employeebackend.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(schema = "esaf", catalog = "esaf", name = "user")
@ToString(exclude = "roles")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_on")
	private Date updatedOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "deleted_on")
	private Date deletedOn;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();
	
	//textAdjust="StretchHeight"
}
