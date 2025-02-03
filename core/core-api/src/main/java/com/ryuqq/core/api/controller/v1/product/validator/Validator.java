package com.ryuqq.core.api.controller.v1.product.validator;

public interface Validator<T> {
	void validate(T target, ValidationResult result, boolean updated);
}
