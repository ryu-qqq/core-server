package com.ryuqq.core.domain.exception;

public class DataNotFoundException extends DomainException {

	public DataNotFoundException(String message) {
		super(404, message);
	}

}
