package com.ryuqq.core.domain.exception;

import org.springframework.validation.BindingResult;

import com.ryuqq.core.enums.ErrorType;

public class DomainException extends RuntimeException {

	private final ErrorType errorType;
	private BindingResult errors;


	public DomainException(int value, String message) {
		super(message);
		this.errorType = ErrorType.of(value);
	}

}
