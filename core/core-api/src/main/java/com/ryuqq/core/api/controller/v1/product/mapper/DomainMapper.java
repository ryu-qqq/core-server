package com.ryuqq.core.api.controller.v1.product.mapper;

import com.ryuqq.core.domain.product.ProductGroupContext;

public interface DomainMapper<T> {

	boolean supports(Object fieldValue);
	ProductGroupContext.Builder map(T source, ProductGroupContext.Builder builder);

}
