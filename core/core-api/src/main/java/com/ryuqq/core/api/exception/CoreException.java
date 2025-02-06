package com.ryuqq.core.api.exception;

import com.ryuqq.core.enums.ErrorType;

public class CoreException extends RuntimeException {

	private final ErrorType errorType;

	public CoreException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public CoreException(ErrorType errorType, Throwable cause) {
		super(cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

}
