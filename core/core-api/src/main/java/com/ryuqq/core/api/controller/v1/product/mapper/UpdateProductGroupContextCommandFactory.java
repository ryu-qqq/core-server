package com.ryuqq.core.api.controller.v1.product.mapper;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.product.coreV2.DefaultCreateProductGroupContextCommand;
import com.ryuqq.core.domain.product.coreV2.ProductGroupContextCommand;
import com.ryuqq.core.enums.ErrorType;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CreateProductGroupContextCommandFactory implements ProductGroupContextCommandFactory {

	private final List<DomainMapper<?>> mappers;

	public CreateProductGroupContextCommandFactory(List<DomainMapper<?>> mappers) {
		this.mappers = mappers;
	}

	@Override
	public ProductGroupContextCommand createCommand(ProductGroupContextCommandRequestDto dto) {
		DefaultCreateProductGroupContextCommand.Builder builder = new DefaultCreateProductGroupContextCommand.Builder();

		for (Field field : getDeclaredFields(dto.getClass())) {
			field.setAccessible(true);
			try {
				Object fieldValue = field.get(dto);
				if (fieldValue != null) {
					DomainMapper<Object> mapper = findMapperOrThrow(fieldValue);
					mapper.map(fieldValue, builder);
				}
			} catch (IllegalAccessException e) {
				throw new CoreException(ErrorType.UNEXPECTED_ERROR, e);
			}
		}

		return builder.build();
	}

	private Field[] getDeclaredFields(Class<?> clazz) {
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
