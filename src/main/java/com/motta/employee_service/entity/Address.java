package com.motta.employee_service.entity;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address")
@SequenceGenerator(name = "Custom_Sequence", sequenceName = "custom_sequence", initialValue = 4, allocationSize = 1)
@Data
@NoArgsConstructor
public class Address {

	public enum AddressType {
		PERMANENT, RESIDENTIAL
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Custom_Sequence")
	private int id;
	private String addressLine1;
	private String addressLine2;
	private String zipCode;

	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	@Column(name = "employee_Id", insertable = false, updatable = false)
	private int employeeId;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp createdAt;

	@Column(name = "modified_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp modifiedAt;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_Id")
	private Employee employee;

	public Address(int id, String addressLine1, String addressLine2, String zipCode, AddressType addressType, int employeeId, Timestamp createdAt, Timestamp modifiedAt) {
		this.id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipCode;
		this.addressType = addressType;
		this.employeeId = employeeId;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
}
