package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductDetailDescriptionRequestDto;
import com.ryuqq.core.domain.product.core.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;

@Component
class ProductDetailDescriptionMapper implements DomainMapper<ProductDetailDescriptionRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductDetailDescriptionRequestDto;
	}

	@Override
	public ProductGroupContextCommandBuilder map(ProductDetailDescriptionRequestDto requestDto,
												 ProductGroupContextCommandBuilder builder) {
		long productGroupId = builder.getProductGroupId().orElse(0L);

		ProductDetailDescriptionCommand productDetailDescriptionCommand =
			ProductDetailDescriptionCommand.of(productGroupId, requestDto.detailDescription());

		builder.withProductDetailDescriptionCommand(productDetailDescriptionCommand);
		return builder;
	}

}
