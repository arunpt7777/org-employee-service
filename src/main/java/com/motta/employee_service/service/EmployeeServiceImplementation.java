package com.motta.employee_service.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motta.employee_service.entity.Address;
import com.motta.employee_service.entity.Employee;
import com.motta.employee_service.exception.AddressNotFoundException;
import com.motta.employee_service.exception.EmployeeAlreadyExistsException;
import com.motta.employee_service.exception.EmployeeNotFoundException;
import com.motta.employee_service.mapper.EmployeeMapper;
import com.motta.employee_service.model.EmployeeDTO;
import com.motta.employee_service.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressService addressService;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

		// CHeck if id already exists
		Optional<Employee> employee = employeeRepository.findById(employeeDTO.getId());
		if (employee.isPresent())
			throw new EmployeeAlreadyExistsException("Employee id = " + employeeDTO.getId() + " already Exists!");
		/*
		 * List<Address> addresses = employee.get().getAddresses(); if
		 * (addresses.isEmpty()) throw new
		 * AddressNotFoundException("Employee must have address details");
		 * 
		 * if (!areAllUnique(addresses)) { throw new
		 * EmployeeHasDuplicateAddressesException("Employee cannot have duplicate Addresses"
		 * ); }
		 */
		// Convert EmployeeDTO into User JPA Entity
		Employee newEmployee = EmployeeMapper.mapToEmployee(employeeDTO);
		Employee savedEmployee = employeeRepository.save(newEmployee);

		// Convert Employee JPA entity to UserDto
		EmployeeDTO savedEmployeeDTO = EmployeeMapper.mapToEmployeeDTO(savedEmployee);
		return savedEmployeeDTO;
	}

	public static <Address> boolean areAllUnique(List<Address> list) {
		return list.stream().allMatch(new HashSet<>()::add);
	}

	@Override
	public EmployeeDTO retrieveEmployeeById(Integer id) {
		Employee employee = employeeRepository.findById(id).get();
		if (employee == null)
			throw new EmployeeNotFoundException("Employee id = " + id + " not found. Please enter different id");
		return EmployeeMapper.mapToEmployeeDTO(employee);
	}

	@Override
	public List<EmployeeDTO> retrieveAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map(EmployeeMapper::mapToEmployeeDTO).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
		Employee existingEmployee = employeeRepository.findById(employeeDTO.getId()).get();
		if (existingEmployee == null)
			throw new EmployeeNotFoundException(
					"Employee id = " + employeeDTO.getId() + " not found. Please enter different id");

		existingEmployee.setAge(employeeDTO.getAge());
		existingEmployee.setEmail(employeeDTO.getEmail());
		existingEmployee.setEmployeeNumber(employeeDTO.getEmployeeNumber());
		existingEmployee.setFirstName(employeeDTO.getFirstName());
		existingEmployee.setLastName(employeeDTO.getLastName());
		existingEmployee.setPhone(employeeDTO.getPhone());
		existingEmployee.setSalaryId(employeeDTO.getSalaryId());
		existingEmployee.setAddresses(employeeDTO.getAddresses());
		existingEmployee.setCreatedAt(employeeDTO.getCreatedAt());
		existingEmployee.setModifiedAt(new Timestamp(System.currentTimeMillis()));

		Employee updatedEmployee = employeeRepository.save(existingEmployee);
		return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Integer id) {
		EmployeeDTO employeeDTO = retrieveEmployeeById(id);
		List<Address> addresses = employeeDTO.getAddresses();
		if (addresses.isEmpty())
			throw new AddressNotFoundException("Employee does not have mandatory addresses");
		addresses.stream().forEach(address -> addressService.deleteAddress(address.getId())); // delete foreign key
																								// referenced addresses
		employeeRepository.deleteById(id);
	}

	@Override
	public List<EmployeeDTO> retrieveAllEmployeesByGender(String gender) {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().filter(emp -> emp.getGender().equalsIgnoreCase(gender))
				.map(EmployeeMapper::mapToEmployeeDTO).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeDTO> findEmployeeByGenderUsingNativeQuery(Integer age, String gender) {
		List<Employee> employeesByGender = employeeRepository.findEmployeeByGenderUsingNativeQuery(age, gender);
		return employeesByGender.stream().map(EmployeeMapper::mapToEmployeeDTO).collect(Collectors.toList());
	}

}
