package com.ryuqq.core.api.exception;

import com.ryuqq.core.enums.ErrorType;

public class CoreException extends RuntimeException {

	private final ErrorType errorType;

	public CoreException(String message, ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}


	public CoreException(String message, ErrorType errorType, Throwable cause) {
		super(message + cause.getMessage(), cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}
