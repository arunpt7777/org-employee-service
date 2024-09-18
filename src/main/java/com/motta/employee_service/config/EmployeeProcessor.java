package com.motta.employee_service.config;

import com.motta.employee_service.entity.Employee;
import org.springframework.batch.item.ItemProcessor;

public class EmployeeProcessor implements ItemProcessor<Employee,Employee> {

    // Processor can be used to provide filter conditions in the data
    @Override
    public Employee process(Employee employee) throws Exception {
        if(employee.getSalaryId()==1) {
            return employee;
        }else{
            return null;
        }
    }
}
