package com.ryuqq.core.domain.product.core;

import java.util.List;

import com.ryuqq.core.enums.OptionType;

public record DefaultProductOptionContextCommand(
	long productGroupId,
	OptionType optionType,
	List<? extends ProductOptionCommand> productCommands

) implements ProductOptionContextCommand {

	@Override
	public ProductOptionContextCommand assignProductGroupId(long productGroupId) {
		List<ProductOptionCommand> assignedProductOptionCommands = productCommands.stream()
			.map(p -> p.assignProductGroupId(productGroupId))
			.toList();

		return new DefaultProductOptionContextCommand(productGroupId, optionType, assignedProductOptionCommands);
	}
}
