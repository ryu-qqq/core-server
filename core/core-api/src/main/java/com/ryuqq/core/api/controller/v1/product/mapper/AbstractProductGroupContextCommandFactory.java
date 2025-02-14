package com.ryuqq.core.api.controller.v1.product.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;
import com.ryuqq.core.enums.ErrorType;

public abstract class AbstractProductGroupContextCommandFactory implements ProductGroupContextCommandFactory {

	private final List<DomainMapper<?>> mappers;

	protected AbstractProductGroupContextCommandFactory(List<DomainMapper<?>> mappers) {
		this.mappers = mappers;
	}

	protected Field[] getDeclaredFields(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}

	@SuppressWarnings("unchecked")
	protected DomainMapper<Object> findMapperOrThrow(Object fieldValue) {
		for (DomainMapper<?> mapper : mappers) {
			if (mapper.supports(fieldValue)) {
				return (DomainMapper<Object>) mapper;
			}
		}
		throw new IllegalArgumentException("No suitable mapper found for field value: " + fieldValue);
	}

	public ProductGroupContextCommand createCommand(Long productGroupId, ProductGroupContextCommandRequestDto dto) {
		ProductGroupContextCommandBuilder builder = createBuilder();

		if (productGroupId != null) {
			builder.withProductGroupId(productGroupId);
		}

		Field[] declaredFields = getDeclaredFields(dto.getClass());

		if(declaredFields.length == 0) {
			throw new CoreException(ErrorType.UNEXPECTED_ERROR, "ProductGroupContextCommandRequestDto class declaredFields cannot be empty");
		}
		Arrays.stream(declaredFields)
			.forEach(field -> {
				field.setAccessible(true);
				try {
					Object fieldValue = field.get(dto);

					if (fieldValue == null) {
						throw new CoreException(ErrorType.INVALID_INPUT_ERROR, "Field " + field.getName() + " cannot be null.");
					}

					DomainMapper<Object> mapper = findMapperOrThrow(fieldValue);
					mapper.map(fieldValue, builder);

				} catch (IllegalAccessException e) {
					throw new CoreException(ErrorType.UNEXPECTED_ERROR, "Access to field " + field.getName() + " failed", e);
				} catch (NullPointerException e) {
					throw new CoreException(ErrorType.INVALID_INPUT_ERROR, "Field " + field.getName() + " is null", e);
				}
			});

		return builder.build();
	}

	protected abstract ProductGroupContextCommandBuilder createBuilder();

}
