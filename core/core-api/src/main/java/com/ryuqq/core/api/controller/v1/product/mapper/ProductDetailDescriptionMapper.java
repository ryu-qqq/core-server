package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductDetailDescriptionRequestDto;
import com.ryuqq.core.domain.product.ProductDetailDescription;
import com.ryuqq.core.domain.product.ProductGroupContext;

@Component
public class ProductDetailDescriptionMapper implements DomainMapper<ProductDetailDescriptionRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductDetailDescriptionRequestDto;
	}

	@Override
	public ProductGroupContext.Builder map(ProductDetailDescriptionRequestDto source,
										   ProductGroupContext.Builder builder) {

		ProductDetailDescription productDetailDescription = ProductDetailDescription.create(source.detailDescription());
		builder.productDetailDescription(productDetailDescription);
		return builder;
	}
}
