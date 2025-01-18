package com.ryuqq.core.storage.db.exception;

import com.ryuqq.core.enums.ErrorType;

import java.util.Map;

public class RdsStorageException extends RuntimeException {

	private final ErrorType errorType;

	public RdsStorageException(String message, ErrorType errorType, Throwable cause) {
		super(errorType.getMessage() + " " + message, cause);
		this.errorType = errorType;
	}

	public RdsStorageException(ErrorType errorType, Throwable cause) {
		super(errorType.getMessage(), cause);
		this.errorType = errorType;
	}

	public RdsStorageException(String message, ErrorType errorType) {
		super(message);
		this.errorType = errorType;
	}


	public ErrorType getErrorType() {
		return errorType;
	}

}
