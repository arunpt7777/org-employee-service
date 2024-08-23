package com.motta.employee_service.service;

import java.util.List;
import java.util.stream.Collectors;
import com.motta.employee_service.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.motta.employee_service.entity.Address;
import com.motta.employee_service.entity.Employee;
import com.motta.employee_service.mapper.EmployeeMapper;
import com.motta.employee_service.model.EmployeeDTO;
import com.motta.employee_service.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

import static com.motta.employee_service.util.EmployeeConstants.*;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Value("${employee.id.initialValue}")
	private Integer initialValueOfPrimaryKey;

	@Value("${employee.employeeNumber.length}")
	private Integer employeeNumberLength;

	@Value("${employee.phoneNumber.length}")
	private Integer phoneNumberLength;

	@Value("${employee.age.min}")
	private Integer employeeMinAge;

	@Value("${employee.age.max}")
	private Integer employeeMaxAge;

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private AddressService addressService;

	@Transactional
	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

	// Check if From and To Dates are valid
		validateEmployeeDTO(employeeDTO);

	// CHeck if id already exists
	Employee employee = repository.findById(employeeDTO.getId()).orElse(null);
		if (employee == null) {
		throw new EmployeeAlreadyExistsException(EXCEPTION_MESSAGE_EMPLOYEE_NOT_FOUND);
	}

	Employee newEmployee = employeeMapper.mapToEmployee(employeeDTO);
	Employee savedEmployee = repository.save(newEmployee);
		log.info(LOG_MESSAGE_EMPLOYEE_PERSISTED, employeeDTO.getId());

	// Convert Employee JPA entity to EmployeeDTO
        return employeeMapper.mapEmployeeDTO(savedEmployee);
}

@Override
public EmployeeDTO retrieveEmployeeById(Integer id) {
	Employee employee = repository.findById(id).orElse(null);
	assert employee != null;
	EmployeeDTO employeeDTO = new EmployeeDTO();
	BeanUtils.copyProperties(employee, employeeDTO);
	return employeeDTO;
}

@Override
public List<EmployeeDTO> retrieveAllEmployees() {
	List<Employee> employees = repository.findAll();
	return employees.stream().map(employeeMapper::mapEmployeeDTO).toList();
}

@Transactional
@Override
public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
	// Check if From and To Dates are valid
	validateEmployeeDTO(employeeDTO);

	Employee existingEmployee = repository.findById(employeeDTO.getId()).orElse(new Employee());
	BeanUtils.copyProperties(existingEmployee, employeeDTO);
	Employee updatedEmployee = repository.save(existingEmployee);
	log.error(LOG_MESSAGE_EMPLOYEE_UPDATE_FAILED, existingEmployee.getId());
	return employeeMapper.mapEmployeeDTO(updatedEmployee);
}

@Transactional
@Override
public void deleteEmployee(Integer id) {
	EmployeeDTO employeeDTO = retrieveEmployeeById(id);
	List<Address> addresses = employeeDTO.getAddresses();
	if (addresses.isEmpty()) {
		throw new AddressNotFoundException(EXCEPTION_MESSAGE_EMPLOYEE_ADDRESSES_NOT_FOUND);
	}
	addresses.forEach(address -> addressService.deleteAddress(address.getId())); // delete foreign key referenced addresses
	repository.deleteById(id);
}

	@Override
	public List<EmployeeDTO> retrieveAllEmployeesByGender(String gender) {
		List<Employee> employees = repository.findAll();
		return employees.stream().filter(emp -> emp.getGender().equalsIgnoreCase(gender))
				.map(employeeMapper::mapEmployeeDTO).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeDTO> findEmployeeByGenderUsingNativeQuery(Integer age, String gender) {
		List<Employee> employeesByGender = repository.findEmployeeByGenderUsingNativeQuery(age, gender);
		return employeesByGender.stream().map(employeeMapper::mapEmployeeDTO).toList();
	}

	@Override
	public void validateEmployeeDTO(EmployeeDTO employeeDTO) {

		if (employeeDTO.getId()==0) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_EMPLOYEE_ID_IS_MANDATORY);
		}

		if (employeeDTO.getId()<initialValueOfPrimaryKey) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_EMPLOYEE_ID_LESS_THAN_INITIAL_VALUE + initialValueOfPrimaryKey);
		}

		if (employeeDTO.getEmployeeNumber()==0) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_EMPLOYEE_NUMBER_IS_MANDATORY);
		}

		if (employeeDTO.getAge()>employeeMaxAge || employeeDTO.getAge() < employeeMinAge) {
			throw new InvalidEmployeeException( EXCEPTION_MESSAGE_INVALID_AGE + employeeMinAge + employeeMaxAge);
		}
		if (employeeDTO.getFirstName() == null) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_FIRST_NAME_IS_MANDATORY);
		}
		if (employeeDTO.getLastName()==null) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_LAST_NAME_IS_MANDATORY);
		}
		if (employeeDTO.getGender()==null) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_GENDER_IS_MANDATORY);
		}

		if (employeeDTO.getEmail()==null) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_EMAIL_IS_MANDATORY);
		}

		if (employeeDTO.getPhone().length() != phoneNumberLength ) {
			throw new InvalidAddressException(EXCEPTION_MESSAGE_INVALID_PHONE_NUMBER + phoneNumberLength);
		}

		if (employeeDTO.getSalaryId()==0) {
			throw new InvalidEmployeeException(EXCEPTION_MESSAGE_SALARY_ID_IS_MANDATORY);
		}
	}
}
