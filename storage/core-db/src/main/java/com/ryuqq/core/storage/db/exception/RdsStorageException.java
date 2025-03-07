package com.ryuqq.core.storage.db.exception;

import com.ryuqq.core.enums.ErrorType;

public class RdsStorageException extends RuntimeException {

	private final ErrorType errorType;

	public RdsStorageException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public RdsStorageException(ErrorType errorType, Throwable cause) {
		super(cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}


}
