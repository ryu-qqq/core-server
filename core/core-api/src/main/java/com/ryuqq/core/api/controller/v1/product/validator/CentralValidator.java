package com.ryuqq.core.api.controller.v1.product.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;

@Component
public class CentralValidator {

	private final List<Validator<ProductGroupContextCommandRequestDto>> validators;

	public CentralValidator(List<Validator<ProductGroupContextCommandRequestDto>> validators) {
		this.validators = validators;
	}

	public ValidationResult validate(ProductGroupContextCommandRequestDto requestDto, boolean updated) {
		ValidationResult result = new ValidationResult();
		validators.forEach(validator -> validator.validate(requestDto, result, updated));
		return result;
	}

}
