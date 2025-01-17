package com.ryuqq.core.storage.db.exception;

import org.springframework.validation.BindingResult;

import com.ryuqq.core.enums.ErrorType;

public class StorageException extends RuntimeException {

	private final ErrorType errorType;
	private BindingResult errors;


	public StorageException(int value, String message) {
		super(message);
		this.errorType = ErrorType.of(value);
	}

}
