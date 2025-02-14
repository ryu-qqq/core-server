package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupImageRequestDto;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;
import com.ryuqq.core.domain.product.core.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.core.ProductGroupImageContextCommand;

@Component
class ProductGroupImageMapper implements DomainMapper<List<ProductGroupImageRequestDto>> {

	@Override
	public boolean supports(Object fieldValue) {
		if (fieldValue instanceof List<?> list && !list.isEmpty()) {
			return list.getFirst() instanceof ProductGroupImageRequestDto;
		}
		return false;
	}

	@Override
	public ProductGroupContextCommandBuilder map(List<ProductGroupImageRequestDto> requestDto,
												 ProductGroupContextCommandBuilder builder) {
		long productGroupId = builder.getProductGroupId().orElse(0L);

		List<ProductGroupImageCommand> productGroupImageCommands = requestDto.stream()
			.map(p -> ProductGroupImageCommand.of(
				productGroupId,
				p.productImageType(),
				p.imageUrl(),
				p.imageUrl()
			))
			.toList();

		ProductGroupImageContextCommand productGroupImageContextCommand = ProductGroupImageContextCommand.of(
			productGroupImageCommands);

		builder.withProductGroupImageContextCommand(productGroupImageContextCommand);
		return builder;
	}

}
