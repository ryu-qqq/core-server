package com.ryuqq.core.api.controller.v1.category.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.RequesterType;

@Component
public class CategoryContextResponseMapperProvider {

	private final Map<RequesterType, CategoryContextResponseMapper<?>> mapperMap;

	public CategoryContextResponseMapperProvider(
		List<CategoryContextResponseMapper<?>> categoryContextResponseMappers) {
		this.mapperMap = categoryContextResponseMappers.stream()
			.collect(Collectors.toMap(
				CategoryContextResponseMapper::getRequesterType,
				mapper -> mapper,
				(existing, replacement) -> {
					throw new IllegalStateException("Duplicate RequesterType exist: " + existing.getRequesterType());
				}
			));
	}

	public CategoryContextResponseMapper<?> getMapper(RequesterType requesterType) {
		CategoryContextResponseMapper<?> categoryContextResponseMapper = mapperMap.get(
			requesterType);

		if(categoryContextResponseMapper == null) {
			throw new IllegalStateException("Not Support RequesterType : " + requesterType);
		}

		return categoryContextResponseMapper;
	}

}
