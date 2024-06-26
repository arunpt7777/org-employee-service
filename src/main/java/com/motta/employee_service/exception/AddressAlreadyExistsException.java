package com.motta.employee_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class AddressAlreadyExistsException extends RuntimeException {

	public AddressAlreadyExistsException(String message) {
		super(message);
	}
}
