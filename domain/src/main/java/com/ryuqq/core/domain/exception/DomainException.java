package com.ryuqq.core.domain.exception;

import com.ryuqq.core.enums.ErrorType;

public class DomainException extends RuntimeException {

	private final ErrorType errorType;

	public DomainException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public DomainException(ErrorType errorType, String message, Throwable cause) {
		super(appendOriginalMessage(message, cause), cause);
		this.errorType = errorType;
	}

	private static String appendOriginalMessage(String message, Throwable cause) {
		if (cause == null || cause.getMessage() == null) {
			return message;
		}
		return message + " | Cause: \n" + cause.getMessage();
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}
