package com.ryuqq.core.domain.exception;

import com.ryuqq.core.enums.ErrorType;

public class AsyncDomainException extends DomainException{

	public AsyncDomainException(ErrorType errorType, String message, Throwable cause) {
		super(errorType, message, cause);
	}
}
