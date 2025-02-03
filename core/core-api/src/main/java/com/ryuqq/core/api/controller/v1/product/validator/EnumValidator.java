package com.ryuqq.core.api.controller.v1.product.validator;

import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
	private Class<? extends Enum<?>> enumClass;

	@Override
	public void initialize(ValidEnum constraintAnnotation) {
		this.enumClass = constraintAnnotation.enumClass();
	}

	@Override
	public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return Stream.of(enumClass.getEnumConstants()).anyMatch(e -> e.equals(value));
	}
}
