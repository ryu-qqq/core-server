package com.ryuqq.core.api.controller.v1.product.mapper;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.enums.ErrorType;

@Component
public class ProductGroupContextDomainMapperHandler {

	private final List<DomainMapper<?>> mappers;

	public ProductGroupContextDomainMapperHandler(List<DomainMapper<?>> mappers) {
		this.mappers = mappers;
	}

	public ProductGroupContext handleToDomain(ProductGroupContextCommandRequestDto dto) {
		ProductGroupContext.Builder builder = ProductGroupContext.builder();

		for (Field field : getDeclaredFields(dto.getClass())) {
			field.setAccessible(true);

			try {
				Object fieldValue = field.get(dto);
				if (fieldValue != null) {
					DomainMapper<Object> mapper = findMapperOrThrow(fieldValue);
					mapper.map(fieldValue, builder);
				}
			} catch (IllegalAccessException e) {
				throw new CoreException(ErrorType.UNEXPECTED_ERROR, "Failed to access field: " + field.getName(), e);
			}
		}

		return builder.build();
	}

	protected Field[] getDeclaredFields(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}

	@SuppressWarnings("unchecked")
	private DomainMapper<Object> findMapperOrThrow(Object fieldValue) {
		for (DomainMapper<?> mapper : mappers) {
			if (mapper.supports(fieldValue)) {
				return (DomainMapper<Object>) mapper;
			}
		}
		throw new IllegalArgumentException("No suitable mapper found for field value: " + fieldValue);
	}


}
