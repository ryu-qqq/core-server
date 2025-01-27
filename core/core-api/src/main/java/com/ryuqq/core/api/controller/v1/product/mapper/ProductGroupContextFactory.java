package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.domain.product.ProductGroupContext;

@Component
public class ProductGroupContextFactory {


	private final ProductGroupContextDomainMapperHandler mapperHandler;

	public ProductGroupContextFactory(ProductGroupContextDomainMapperHandler mapperHandler) {
		this.mapperHandler = mapperHandler;
	}

	public ProductGroupContext createFromDto(ProductGroupContextCommandRequestDto dto) {
		return mapperHandler.handleToDomain(dto);
	}

}
