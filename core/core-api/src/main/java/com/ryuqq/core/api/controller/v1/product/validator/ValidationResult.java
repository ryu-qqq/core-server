package com.ryuqq.core.api.controller.v1.product.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationResult {

	private final List<String> errors = new ArrayList<>();

	public void addError(String error) {
		errors.add(error);
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public List<String> getErrors() {
		return Collections.unmodifiableList(errors);
	}


	public String getErrorsToString(){
		return String.join("\n", getErrors());
	}

}
