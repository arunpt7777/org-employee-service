package com.motta.employee_service.mapper;

import com.motta.employee_service.entity.Employee;
import com.motta.employee_service.model.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {


	// Convert Scheme JPA Entity into SchemeDTO
	public EmployeeDTO mapEmployeeDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
		return employeeDTO;
	}

	// Convert SchemeDTO into Scheme JPA Entity
	public Employee mapToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		return employee;
	}


}
