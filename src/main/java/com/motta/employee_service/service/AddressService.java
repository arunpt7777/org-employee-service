package com.motta.employee_service.service;

import java.util.List;

import com.motta.employee_service.model.AddressDTO;

public interface AddressService {

	AddressDTO createAddress(AddressDTO addressDTO);

	AddressDTO retrieveAddressById(Integer id);

	List<AddressDTO> retrieveAllAddresss();

	AddressDTO updateAddress(AddressDTO addressDTO);

	void deleteAddress(Integer id);

	List<AddressDTO> retrieveAllAddressesByType(String type);

}
