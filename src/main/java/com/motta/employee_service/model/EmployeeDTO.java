package com.motta.employee_service.model;

import java.sql.Timestamp;
import java.util.List;

import com.motta.employee_service.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {

	private int id;
	private int employeeNumber;
	private int age;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String gender;
	private int salaryId;
	private List<Address> addresses;
	public Timestamp modifiedAt;
	public Timestamp createdAt;

	public EmployeeDTO(int id, int employeeNumber, int age, String firstName, String lastName, String email, String phone, String gender, int salaryId, Timestamp modifiedAt, Timestamp createdAt) {
		this.id = id;
		this.employeeNumber = employeeNumber;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.salaryId = salaryId;
		this.modifiedAt = modifiedAt;
		this.createdAt = createdAt;
	}
}
