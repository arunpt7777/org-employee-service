package com.motta.employee_service.mapper;

import com.motta.employee_service.entity.Address;
import com.motta.employee_service.entity.Address.AddressType;
import com.motta.employee_service.model.AddressDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

	// Convert Scheme JPA Entity into SchemeDTO
	public AddressDTO mapToAddressDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(address, addressDTO);
		return addressDTO;
	}

	// Convert SchemeDTO into Scheme JPA Entity
	public Address mapToAddress(AddressDTO addressDTO) {
		Address address = new Address();
		BeanUtils.copyProperties(addressDTO, address);
		return address;
	}
}
