package com.motta.employee_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.motta.employee_service.exception.InvalidAddressException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.motta.employee_service.entity.Address;
import com.motta.employee_service.entity.Address.AddressType;
import com.motta.employee_service.exception.AddressAlreadyExistsException;
import com.motta.employee_service.mapper.AddressMapper;
import com.motta.employee_service.model.AddressDTO;
import com.motta.employee_service.repository.AddressRepository;

import jakarta.transaction.Transactional;

import static com.motta.employee_service.util.AddressConstants.*;

@Transactional
@Slf4j
@Service
public class AddressServiceImplementation implements AddressService {

	@Autowired
	private AddressMapper addressMapper;

	@Value("${address.id.initialValue}")
	private Integer initialValueOfPrimaryKey;

	@Value("${address.zipcode.length}")
	private Integer zipCodeLength;

	@Autowired
	private AddressRepository repository;

	@Override
	public AddressDTO createAddress(AddressDTO addressDTO) {

		// Check if From and To Dates are valid
		validateAddressDTO(addressDTO);

		// Check if id already exists
		Address address = repository.findById(addressDTO.getId()).orElse(null);
		if (address == null) {
			throw new AddressAlreadyExistsException(EXCEPTION_MESSAGE_ADDRESS_NOT_FOUND);
		}

		Address newAddress = addressMapper.mapToAddress(addressDTO);
		Address savedAddress = repository.save(newAddress);
		log.info(LOG_MESSAGE_ADDRESS_PERSISTED, addressDTO.getId());

		// Convert Address JPA entity to AddressDTO
		return addressMapper.mapToAddressDTO(address);
	}

	@Override
	public AddressDTO retrieveAddressById(Integer id) {
		Address address = repository.findById(id).orElse(null);
		assert address != null;
		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(address, addressDTO);
		return addressDTO;	}

	@Override
	public List<AddressDTO> retrieveAllAddresss() {
		List<Address> addresses = repository.findAll();
		return addresses.stream().map(addressMapper::mapToAddressDTO).collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public AddressDTO updateAddress(AddressDTO addressDTO) {
		// Check if From and To Dates are valid
		validateAddressDTO(addressDTO);

		Address existingAddress = repository.findById(addressDTO.getId()).orElse(new Address());
		BeanUtils.copyProperties(existingAddress, addressDTO);
		Address updatedAddress = repository.save(existingAddress);
		log.error(LOG_MESSAGE_ADDRESS_UPDATE_FAILED, existingAddress.getId());
		return addressMapper.mapToAddressDTO(updatedAddress);
	}

	@Transactional
	@Override
	public void deleteAddress(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<AddressDTO> retrieveAllAddressesByType(String addressType) {
		List<Address> addresses = repository.findAll();
		return addresses.stream().filter(emp -> emp.getAddressType().toString().equalsIgnoreCase(addressType))
				.map(addressMapper::mapToAddressDTO).collect(Collectors.toList());
	}

	@Override
	public void validateAddressDTO(AddressDTO addressDTO) {
		if (addressDTO.getId()== 0) {
			throw new InvalidAddressException(EXCEPTION_MESSAGE_ADDRESS_ID_IS_MANDATORY);
		}

		if (addressDTO.getId()<initialValueOfPrimaryKey) {
			throw new InvalidAddressException(EXCEPTION_MESSAGE_ADDRESS_ID_LESS_THAN_INITIAL_VALUE + initialValueOfPrimaryKey);
		}

		if (addressDTO.getAddressLine1()==null) {
			throw new InvalidAddressException(EXCEPTION_MESSAGE_ADDRESS_LINE1_IS_MANDATORY);
		}
		if (addressDTO.getAddressLine2()==null) {
			throw new InvalidAddressException(EXCEPTION_MESSAGE_ADDRESS_LINE2_IS_MANDATORY);
		}

		if (addressDTO.getZipCode().length() != zipCodeLength ) {
			throw new InvalidAddressException(EXCEPTION_MESSAGE_ZIPCODE_LENGTH_INVALID + zipCodeLength);
		}
	}
}
