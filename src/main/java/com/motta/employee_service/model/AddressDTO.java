package com.motta.employee_service.model;

import java.sql.Timestamp;

import com.motta.employee_service.entity.Employee;

public class AddressDTO {

	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String zipCode;
	private String addressType;
	private Integer employeeId;
	private Employee employee;
	public Timestamp modifiedAt;
	public Timestamp createdAt;

	public AddressDTO() {
	}

	public AddressDTO(Integer id, String addressLine1, String addressLine2, String zipCode, String addressType,
			Integer employeeId, Employee employee, Timestamp modifiedAt, Timestamp createdAt) {
		super();
		this.id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipCode;
		this.addressType = addressType;
		this.employeeId = employeeId;
		this.employee = employee;
		this.modifiedAt = modifiedAt;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "AddressDTO [id=" + id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", zipCode=" + zipCode + ", addressType=" + addressType + ", employeeId=" + employeeId + ", employee="
				+ employee + ", modifiedAt=" + modifiedAt + ", createdAt=" + createdAt + "]";
	}

}
