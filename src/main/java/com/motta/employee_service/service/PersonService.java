package com.motta.employee_service.service;

import com.motta.employee_service.model.EmployeeDTO;
import com.motta.employee_service.model.PersonDTO;

public interface PersonService {

	PersonDTO copyEmployeeToPerson(EmployeeDTO employeeDTO);

}
