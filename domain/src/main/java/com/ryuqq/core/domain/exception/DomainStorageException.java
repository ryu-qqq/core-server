package com.ryuqq.core.domain.exception;

import com.ryuqq.core.enums.ErrorType;

public class DomainStorageException extends DomainException {

	private final ErrorType errorType;

	public DomainStorageException(String message, ErrorType errorType, Throwable cause) {
		super(message, cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}
