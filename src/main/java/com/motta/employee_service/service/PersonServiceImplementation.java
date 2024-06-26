package com.motta.employee_service.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.motta.employee_service.model.EmployeeDTO;
import com.motta.employee_service.model.PersonDTO;

@Service
public class PersonServiceImplementation implements PersonService {

	@Override
	public PersonDTO copyEmployeeToPerson(EmployeeDTO employeeDTO) {
		PersonDTO personDTO = new PersonDTO();
		BeanUtils.copyProperties(employeeDTO, personDTO);
		return personDTO;
	}

}
