package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductInsertRequestDto;
import com.ryuqq.core.api.controller.v1.product.request.ProductOptionInsertRequestDto;
import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.domain.product.core.ProductCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;
import com.ryuqq.core.domain.product.core.ProductOptionCommand;
import com.ryuqq.core.domain.product.core.ProductOptionContextCommand;

@Component
class ProductMapper implements DomainMapper<List<ProductInsertRequestDto>>{

	@Override
	public boolean supports(Object fieldValue) {
		if (fieldValue instanceof List<?> list && !list.isEmpty()) {
			return list.getFirst() instanceof ProductInsertRequestDto;
		}
		return false;
	}

	@Override
	public ProductGroupContextCommandBuilder map(List<ProductInsertRequestDto> requestDtos, ProductGroupContextCommandBuilder builder) {
		long productGroupId = builder.getProductGroupId().orElse(0L);

		List<ProductOptionCommand> productContexts = requestDtos.stream()
			.map(p ->{
					ProductCommand productCommand = ProductCommand.of(
						p.productId(), productGroupId, p.soldOut(),
						p.displayed(), p.quantity(),
						p.additionalPrice(), false
					);
					return ProductOptionCommand.of(productCommand, false, toOptionContexts(p.options()));
				}
			)
			.toList();

		ProductOptionContextCommand productOptionContextCommand = ProductOptionContextCommand.of(productGroupId, builder.getOptionType(), productContexts);

		builder.withProductContextCommand(productOptionContextCommand);
		return builder;
	}

	private List<OptionContextCommand> toOptionContexts(List<ProductOptionInsertRequestDto> options) {
		return options.stream()
			.map(o -> OptionContextCommand.of(0, 0,0, o.optionName(), o.optionValue()))
			.distinct()
			.collect(Collectors.toList());
	}


}
