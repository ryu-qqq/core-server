package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupImageRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;

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
		int imageSortCounter = 2;

		List<ProductGroupImageCommand> productGroupImageCommands = new ArrayList<>();

		for (ProductGroupImageRequestDto img : requestDto) {
			if (img.productImageType().isMain()) {
				productGroupImageCommands.add(ProductGroupImageCommand.of(
					productGroupId,
					img.productImageType(),
					img.imageUrl(),
					img.imageUrl(),
					1
				));
			} else {
				productGroupImageCommands.add(ProductGroupImageCommand.of(
					productGroupId,
					img.productImageType(),
					img.imageUrl(),
					img.imageUrl(),
					imageSortCounter
				));
				imageSortCounter++;
			}
		}

		ProductGroupImageContextCommand productGroupImageContextCommand = ProductGroupImageContextCommand.of(
			productGroupImageCommands);

		builder.withProductGroupImageContextCommand(productGroupImageContextCommand);
		return builder;
	}

}
