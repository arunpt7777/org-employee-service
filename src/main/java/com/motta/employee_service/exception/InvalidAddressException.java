package com.motta.employee_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidAddressException extends RuntimeException {

	public InvalidAddressException(String message) {
		super(message);
	}
}
