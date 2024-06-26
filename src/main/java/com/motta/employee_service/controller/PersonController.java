package com.motta.employee_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.motta.employee_service.model.EmployeeDTO;
import com.motta.employee_service.model.PersonDTO;
import com.motta.employee_service.service.EmployeeService;
import com.motta.employee_service.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PersonService personService;

	// Retrieve PersonDTO for an EmployeeIdREST API
	@GetMapping("/getperson/{employeeId}")
	public ResponseEntity<PersonDTO> getPersonForEmployeeId(@PathVariable("employeeId") Integer employeeId) {
		EmployeeDTO employeeDTO = employeeService.retrieveEmployeeById(employeeId);
		PersonDTO personTO = personService.copyEmployeeToPerson(employeeDTO);
		return new ResponseEntity<>(personTO, HttpStatus.OK);
	}
}
