package com.motta.employee_service.model;

import java.sql.Timestamp;

import com.motta.employee_service.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {

	private int id;
	private String addressLine1;
	private String addressLine2;
	private String zipCode;
	private String addressType;
	private int employeeId;
	private Employee employee;
	public Timestamp modifiedAt;
	public Timestamp createdAt;

	public AddressDTO(int id, String addressLine1, String addressLine2, String zipCode, String addressType, int employeeId, Timestamp modifiedAt, Timestamp createdAt) {
		this.id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipCode;
		this.addressType = addressType;
		this.employeeId = employeeId;
		this.modifiedAt = modifiedAt;
		this.createdAt = createdAt;
	}
}
