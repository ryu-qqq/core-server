package com.ryuqq.core.domain.product;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;
import com.ryuqq.core.enums.ErrorType;

@Service
public class ProductGroupDomainBusinessValidator {

	private final List<ProductGroupDomainValidator<?>> validators;

	public ProductGroupDomainBusinessValidator(List<ProductGroupDomainValidator<?>> validators) {
		this.validators = validators;
	}

	protected Field[] getDeclaredFields(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}

	public void validate(ProductGroupContextCommand productGroupContextCommand, boolean updated) {
		ValidationResult result = new ValidationResult();

		Field[] declaredFields = getDeclaredFields(productGroupContextCommand.getClass());

		if(declaredFields.length == 0) {
			throw new DomainException(ErrorType.UNEXPECTED_ERROR, "ProductGroupContextCommand class declaredFields cannot be empty");
		}

		Arrays.stream(declaredFields)
			.forEach(field -> {
							field.setAccessible(true);
							try {
								Object fieldValue = field.get(productGroupContextCommand);

								if (fieldValue == null) {
									throw new DomainException(ErrorType.INVALID_INPUT_ERROR, "Field " + field.getName() + " cannot be null.");
								}

								findMapper(fieldValue)
									.ifPresent(validator -> validator.validate(field, result, updated));

							} catch (IllegalAccessException e) {
								throw new RuntimeException("Field access error", e);
							}
						});

		result.throwIfInvalid();
	}


	@SuppressWarnings("unchecked")
	protected Optional<ProductGroupDomainValidator<Object>> findMapper(Object fieldValue) {
		return validators.stream()
			.filter(validator -> validator.supports(fieldValue.getClass()))
			.map(validator -> (ProductGroupDomainValidator<Object>) validator)
			.findFirst();
	}


}
