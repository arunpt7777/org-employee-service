package com.motta.employee_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.motta.employee_service.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM employee_management.employee e WHERE e.age <= :age AND e.gender=:gender")
	List<Employee> findEmployeeByGenderUsingNativeQuery(@Param("age") int age, @Param("gender") String gender);

}
