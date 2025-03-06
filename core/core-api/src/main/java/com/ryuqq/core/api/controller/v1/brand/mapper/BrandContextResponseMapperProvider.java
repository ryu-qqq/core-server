package com.ryuqq.core.api.controller.v1.brand.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.RequesterType;

@Component
public class BrandContextResponseMapperProvider {

	private final Map<RequesterType, BrandContextResponseMapper<?>> mapperMap;

	public BrandContextResponseMapperProvider(
		List<BrandContextResponseMapper<?>> brandContextResponseMappers) {
		this.mapperMap = brandContextResponseMappers.stream()
			.collect(Collectors.toMap(
				BrandContextResponseMapper::getRequesterType,
				mapper -> mapper,
				(existing, replacement) -> {
					throw new IllegalStateException("Duplicate RequesterType exist: " + existing.getRequesterType());
				}
			));
	}

	public BrandContextResponseMapper<?> getMapper(RequesterType requesterType) {
		BrandContextResponseMapper<?> brandContextResponseMapper = mapperMap.get(
			requesterType);

		if(brandContextResponseMapper == null) {
			throw new IllegalStateException("Not Support RequesterType : " + requesterType);
		}

		return brandContextResponseMapper;
	}

}
