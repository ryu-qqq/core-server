package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.enums.ErrorType;

public class ValidationResult {

	private final List<String> errors = new ArrayList<>();

	public void addError(String error) {
		errors.add(error);
	}


	boolean hasErrors() {
		return !errors.isEmpty();
	}

	List<String> getErrors() {
		return Collections.unmodifiableList(errors);
	}

	String getErrorsToString(){
		return String.join("\n", getErrors());
	}

	public void throwIfInvalid() {
		if (hasErrors()) {
			throw new DomainException(ErrorType.BAD_REQUEST_ERROR, getErrorsToString());
		}
	}

}
