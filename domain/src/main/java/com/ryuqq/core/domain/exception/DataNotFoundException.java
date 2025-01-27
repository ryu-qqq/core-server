package com.ryuqq.core.domain.exception;

import com.ryuqq.core.enums.ErrorType;

public class DataNotFoundException extends DomainException {

	public DataNotFoundException(String message) {
		super(ErrorType.NOT_FOUND_ERROR, message);
	}

}
