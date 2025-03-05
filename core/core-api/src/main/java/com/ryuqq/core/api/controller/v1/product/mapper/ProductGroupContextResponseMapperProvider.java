package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.core.enums.RequesterType;

@Component
public class ProductGroupContextResponseMapperProvider {

	private final Map<RequesterType, ProductGroupContextResponseMapper<? extends ProductGroupContextResponse, ?>> mapperMap;

	public ProductGroupContextResponseMapperProvider(List<ProductGroupContextResponseMapper<? extends ProductGroupContextResponse, ?>> productGroupContextResponseMappers) {
		this.mapperMap = productGroupContextResponseMappers.stream()
			.collect(Collectors.toMap(
				ProductGroupContextResponseMapper::getRequesterType,
				mapper -> mapper,
				(existing, replacement) -> {
					throw new IllegalStateException("Duplicate RequesterType exist: " + existing.getRequesterType());
				}
			));
	}

	public ProductGroupContextResponseMapper<? extends ProductGroupContextResponse, ?> getMapper(RequesterType requesterType) {
		ProductGroupContextResponseMapper<? extends ProductGroupContextResponse, ?> productGroupContextResponseMapper = mapperMap.get(
			requesterType);

		if(productGroupContextResponseMapper == null) {
			throw new IllegalStateException("Not Support RequesterType : " + requesterType);
		}

		return productGroupContextResponseMapper;
	}

}
