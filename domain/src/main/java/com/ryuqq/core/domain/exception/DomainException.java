package com.ryuqq.core.domain.exception;

import com.ryuqq.core.enums.ErrorType;

import org.springframework.validation.BindingResult;

public class DomainException extends RuntimeException {

	private final ErrorType errorType;
	private BindingResult errors;


	public DomainException(int value, String message) {
		super(message);
		this.errorType = ErrorType.of(value);
	}

}
