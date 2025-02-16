package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupInsertRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;

@Component
class ProductGroupMapper implements DomainMapper<ProductGroupInsertRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductGroupInsertRequestDto;
	}

	@Override
	public ProductGroupContextCommandBuilder map(ProductGroupInsertRequestDto requestDto, ProductGroupContextCommandBuilder builder) {

		long productGroupId = builder.getProductGroupId().orElse(requestDto.productGroupId());

		ProductGroupCommand productGroupCommand = ProductGroupCommand.of(
			productGroupId,
			requestDto.sellerId(),
			requestDto.categoryId(),
			requestDto.brandId(),
			requestDto.productGroupName(),
			requestDto.styleCode(),
			requestDto.productCondition(),
			requestDto.managementType(),
			requestDto.optionType(),
			requestDto.regularPrice(),
			requestDto.currentPrice(),
			requestDto.soldOut(),
			requestDto.displayed(),
			requestDto.keywords());

		builder.withProductGroupCommand(productGroupCommand);
		builder.withOptionType(requestDto.optionType());
		return builder;
	}



}
