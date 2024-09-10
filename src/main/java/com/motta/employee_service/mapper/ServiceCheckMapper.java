package com.motta.employee_service.mapper;

import com.motta.employee_service.entity.ServiceCheck;
import com.motta.employee_service.model.ServiceCheckDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceCheckMapper {


	// Convert Scheme JPA Entity into SchemeDTO
	public ServiceCheckDTO mapServiceCheckDTO(ServiceCheck serviceCheck) {
		ServiceCheckDTO serviceCheckDTO = new ServiceCheckDTO();
		BeanUtils.copyProperties(serviceCheck, serviceCheckDTO);
		return serviceCheckDTO;
	}

	// Convert SchemeDTO into Scheme JPA Entity
	public ServiceCheck mapToServiceCheck(ServiceCheckDTO serviceCheckDTO) {
		ServiceCheck serviceCheck = new ServiceCheck();
		BeanUtils.copyProperties(serviceCheckDTO, serviceCheck);
		return serviceCheck;
	}
	
	
}
