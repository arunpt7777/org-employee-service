package com.motta.employee_service.mapper;

import com.motta.employee_service.entity.Employee;
import com.motta.employee_service.model.EmployeeDTO;

public class EmployeeMapper {

	// Convert Employee JPA Entity into EmployeeDTO
	public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getEmployeeNumber(), employee.getAge(),
				employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(),
				employee.getGender(), employee.getSalaryId(), employee.getAddresses(), employee.getModifiedAt(),
				employee.getCreatedAt());
		return employeeDTO;
	}

	// Convert EmployeeDTO into Employee JPA Entity
	public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee(employeeDTO.getId(), employeeDTO.getEmployeeNumber(), employeeDTO.getAge(),
				employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail(), employeeDTO.getPhone(),
				employeeDTO.getGender(), employeeDTO.getSalaryId(), employeeDTO.getAddresses(),
				employeeDTO.getCreatedAt(), employeeDTO.getModifiedAt());
		return employee;
	}
}
