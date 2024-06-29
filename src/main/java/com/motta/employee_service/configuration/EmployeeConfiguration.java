package com.motta.employee_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfiguration {

	@Bean
	public ModelMapper modelMapperBean() {
		return new ModelMapper();
	}
}
