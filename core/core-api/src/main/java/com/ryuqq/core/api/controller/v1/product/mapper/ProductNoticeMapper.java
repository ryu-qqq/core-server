package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductNoticeInsertRequestDto;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;
import com.ryuqq.core.domain.product.core.ProductNoticeCommand;

@Component
class ProductNoticeMapper implements DomainMapper<ProductNoticeInsertRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductNoticeInsertRequestDto;
	}

	@Override
	public ProductGroupContextCommandBuilder map(ProductNoticeInsertRequestDto requestDto, ProductGroupContextCommandBuilder builder) {
		long productGroupId = builder.getProductGroupId().orElse(0L);

		ProductNoticeCommand productNoticeCommand = ProductNoticeCommand.of(
			productGroupId,
			requestDto.material(),
			requestDto.color(),
			requestDto.size(),
			requestDto.maker(),
			requestDto.origin(),
			requestDto.washingMethod(),
			requestDto.yearMonth(),
			requestDto.assuranceStandard(),
			requestDto.asPhone());

		builder.withProductNoticeCommand(productNoticeCommand);
		return builder;
	}

}
