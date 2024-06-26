package com.motta.employee_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motta.employee_service.entity.Address;
import com.motta.employee_service.entity.Address.AddressType;
import com.motta.employee_service.exception.AddressAlreadyExistsException;
import com.motta.employee_service.exception.AddressNotFoundException;
import com.motta.employee_service.mapper.AddressMapper;
import com.motta.employee_service.model.AddressDTO;
import com.motta.employee_service.repository.AddressRepository;

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImplementation implements AddressService {

	@Autowired
	private AddressRepository repository;

	@Transactional
	@Override
	public AddressDTO createAddress(AddressDTO addressDTO) {

		// CHeck if id already exists
		Optional<Address> address = repository.findById(addressDTO.getId());
		if (address.isPresent())
			throw new AddressAlreadyExistsException("Address id = " + addressDTO.getId() + " already Exists!");

		// Convert AddressDTO into User JPA Entity
		Address newAddress = AddressMapper.mapToAddress(addressDTO);
		Address savedAddress = repository.save(newAddress);

		// Convert Address JPA entity to UserDto
		AddressDTO savedAddressDTO = AddressMapper.mapToAddressDTO(savedAddress);
		return savedAddressDTO;
	}

	@Override
	public AddressDTO retrieveAddressById(Integer id) {
		Address address = repository.findById(id).get();
		if (address == null)
			throw new AddressNotFoundException("Address id = " + id + " not found. Please enter different id");
		return AddressMapper.mapToAddressDTO(address);
	}

	@Override
	public List<AddressDTO> retrieveAllAddresss() {
		List<Address> addresss = repository.findAll();
		return addresss.stream().map(AddressMapper::mapToAddressDTO).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public AddressDTO updateAddress(AddressDTO addressDTO) {
		Address existingAddress = repository.findById(addressDTO.getId()).get();
		if (existingAddress == null)
			throw new AddressNotFoundException(
					"Address id = " + addressDTO.getId() + " not found. Please enter different id");

		AddressType addressType = AddressType.valueOf(addressDTO.getAddressType());

		existingAddress.setAddressLine1(addressDTO.getAddressLine1());
		existingAddress.setAddressLine2(addressDTO.getAddressLine2());
		existingAddress.setZipCode(addressDTO.getZipCode());
		existingAddress.setAddressType(addressType);
		existingAddress.setEmployeeId(addressDTO.getEmployeeId());

		Address updatedAddress = repository.save(existingAddress);
		return AddressMapper.mapToAddressDTO(updatedAddress);
	}

	@Transactional
	@Override
	public void deleteAddress(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<AddressDTO> retrieveAllAddressesByType(String addressType) {
		List<Address> addresss = repository.findAll();
		return addresss.stream().filter(emp -> emp.getAddressType().toString().equalsIgnoreCase(addressType))
				.map(AddressMapper::mapToAddressDTO).collect(Collectors.toList());
	}

}
