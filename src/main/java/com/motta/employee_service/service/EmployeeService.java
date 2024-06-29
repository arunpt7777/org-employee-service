package com.motta.employee_service.service;

import java.util.List;

import com.motta.employee_service.model.EmployeeDTO;

public interface EmployeeService {

	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	EmployeeDTO retrieveEmployeeById(Integer id);

	List<EmployeeDTO> retrieveAllEmployees();

	EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

	void deleteEmployee(Integer id);

	List<EmployeeDTO> retrieveAllEmployeesByGender(String gender);

	List<EmployeeDTO> findEmployeeByGenderUsingNativeQuery(Integer age, String gender);

}
