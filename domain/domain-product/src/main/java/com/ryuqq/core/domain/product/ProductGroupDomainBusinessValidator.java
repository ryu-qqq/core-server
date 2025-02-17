package com.ryuqq.core.domain.product;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
					Class<?> fieldType = field.getType();

					Optional<ProductGroupDomainValidator<Object>> mapper = findMapper(fieldType);

					if (mapper.isPresent()) {
						ProductGroupDomainValidator<Object> validator = mapper.get();


						String fieldName = field.getName();
						String getterMethodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
						Method getterMethod = productGroupContextCommand.getClass().getMethod(getterMethodName);

						Object fieldValue = getterMethod.invoke(productGroupContextCommand);

						if (fieldValue == null) {
							throw new DomainException(ErrorType.INVALID_INPUT_ERROR, "Field " + field.getName() + " cannot be null.");
						}

						validator.validate(fieldValue, result, updated);
					}
				} catch (NoSuchMethodException e) {
					throw new RuntimeException("No getter method found for field: " + field.getName(), e);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException("Failed to access value of field: " + field.getName(), e);
				}
			});

		result.throwIfInvalid();
	}


	@SuppressWarnings("unchecked")
	protected Optional<ProductGroupDomainValidator<Object>> findMapper(Class<?> fieldType) {
		return validators.stream()
			.filter(validator -> validator.supports(fieldType))
			.map(validator -> (ProductGroupDomainValidator<Object>) validator)
			.findFirst();
	}


}
