package com.ryuqq.core.storage.db.exception;

import com.ryuqq.core.enums.ErrorType;

import org.springframework.validation.BindingResult;

public class StorageException extends RuntimeException {

	private final ErrorType errorType;
	private BindingResult errors;


	public StorageException(int value, String message) {
		super(message);
		this.errorType = ErrorType.of(value);
	}

}
