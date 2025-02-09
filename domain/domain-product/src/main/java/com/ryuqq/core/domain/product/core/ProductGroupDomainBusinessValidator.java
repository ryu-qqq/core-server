package com.ryuqq.core.domain.product.core;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductGroupDomainBusinessValidator {

	private final List<ProductGroupDomainValidator<ProductGroupContextCommand>> validators;

	public ProductGroupDomainBusinessValidator(List<ProductGroupDomainValidator<ProductGroupContextCommand>> validators) {
		this.validators = validators;
	}

	public void validate(ProductGroupContextCommand productGroupContextCommand, boolean updated) {
		ValidationResult result = new ValidationResult();

		for (Field field : productGroupContextCommand.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				Object fieldValue = field.get(productGroupContextCommand);
				if (fieldValue != null) {
					ProductGroupDomainValidator<Object> validator = findMapperOrThrow(fieldValue);
					validator.validate(field, result, updated);
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Field access error", e);
			}
		}

		result.throwIfInvalid();
	}


	@SuppressWarnings("unchecked")
	protected ProductGroupDomainValidator<Object> findMapperOrThrow(Object fieldValue) {
		for (ProductGroupDomainValidator<?> mapper : validators) {
			if (mapper.supports(fieldValue)) {
				return (ProductGroupDomainValidator<Object>) mapper;
			}
		}
		throw new IllegalArgumentException("No suitable mapper found for field value: " + fieldValue);
	}

}
