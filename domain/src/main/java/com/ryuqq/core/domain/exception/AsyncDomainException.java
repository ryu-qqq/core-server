package com.ryuqq.core.domain.exception;

import com.ryuqq.core.enums.ErrorType;

public class AsyncDomainException extends DomainException{

	public AsyncDomainException(ErrorType errorType, Throwable cause) {
		super(errorType, cause);
	}

}
