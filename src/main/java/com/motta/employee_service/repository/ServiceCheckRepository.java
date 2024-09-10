package com.motta.employee_service.repository;

import com.motta.employee_service.entity.Employee;
import com.motta.employee_service.entity.ServiceCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceCheckRepository extends JpaRepository<ServiceCheck, Integer> {

}
