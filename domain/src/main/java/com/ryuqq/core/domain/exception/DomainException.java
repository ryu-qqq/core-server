package com.ryuqq.core.domain.exception;

import com.ryuqq.core.enums.ErrorType;

public class DomainException extends RuntimeException {

	private final ErrorType errorType;

	public DomainException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public DomainException(ErrorType errorType, String message, Throwable cause) {
		super(message, cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}
