package com.motta.employee_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motta.employee_service.model.AddressDTO;
import com.motta.employee_service.service.AddressService;

import jakarta.validation.Valid;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	// create Address REST API
	@PostMapping("/addresses")
	public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
		AddressDTO savedAddress = addressService.createAddress(addressDTO);
		return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
	}

	// Retrieve Address by id REST API
	@GetMapping("/addresses/{id}")
	public ResponseEntity<AddressDTO> retrieveAddressById(@PathVariable("id") Integer id) {
		AddressDTO address = addressService.retrieveAddressById(id);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	// Retrieve Address by id using RequestParam REST API
	// For example, http://localhost:8080/address?id=10001
	@GetMapping("/address")
	public ResponseEntity<AddressDTO> retrieveAddressByIdRequestParam(@RequestParam Integer id) {
		AddressDTO address = addressService.retrieveAddressById(id);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	// Retrieve All Addresses REST API
	@GetMapping("/addresses")
	public ResponseEntity<List<AddressDTO>> getAllAddresss() {
		List<AddressDTO> addresss = addressService.retrieveAllAddresss();
		return new ResponseEntity<>(addresss, HttpStatus.OK);
	}

	// Update Address REST API
	@PutMapping("/addresses/{id}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable("id") Integer id, @RequestBody AddressDTO address) {
		address.setId(id);
		AddressDTO updatedAddress = addressService.updateAddress(address);
		return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
	}

	// Delete Address REST API
	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable("id") Integer id) {
		addressService.deleteAddress(id);
		return new ResponseEntity<>("Address successfully deleted!", HttpStatus.OK);
	}

	// Retrieve All Addresses by type REST API
	@GetMapping("/address/type/{type}")
	public ResponseEntity<List<AddressDTO>> getAllAddressesByType(@PathVariable("type") String addressType) {
		List<AddressDTO> typeOfAddresses = addressService.retrieveAllAddressesByType(addressType);
		return new ResponseEntity<>(typeOfAddresses, HttpStatus.OK);
	}
}
