package com.ryuqq.core.external;

import com.ryuqq.core.enums.ErrorType;

public class ExternalSiteException extends RuntimeException {

	private final ErrorType errorType;


	public ExternalSiteException(ErrorType errorType,String message) {
		super(message);
		this.errorType = errorType;
	}

	public ExternalSiteException(ErrorType errorType, String message, Throwable cause) {
		super(errorType.getMessage() + " " + message + (cause != null ? " | Cause: \n" + cause.getMessage() : ""), cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}
