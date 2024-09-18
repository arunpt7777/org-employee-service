package com.motta.employee_service.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee")
@SequenceGenerator(name = "Custom_Sequence", sequenceName = "custom_sequence", initialValue = 1000, allocationSize = 1)
@Data
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Custom_Sequence")
	private int id;

	@Column(name = "EMPLOYEE_NUMBER")
	private int employeeNumber;

	@Column(name = "AGE")
	private int age;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phone;

	@Column(name = "SALARY_ID")
	private int salaryId;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp createdAt;

	@Column(name = "modified_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp modifiedAt;

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Address> addresses;


}
