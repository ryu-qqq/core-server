package com.ryuqq.core.api.controller.v1.product.mapper;

import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;

public interface DomainMapper<T> {

	boolean supports(Object fieldValue);
	ProductGroupContextCommandBuilder map(T source, ProductGroupContextCommandBuilder builder);

}
