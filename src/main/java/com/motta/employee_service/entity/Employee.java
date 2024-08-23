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
	private int employeeNumber;
	private int age;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phone;
	private int salaryId;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp createdAt;

	@Column(name = "modified_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp modifiedAt;

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Address> addresses;


}
