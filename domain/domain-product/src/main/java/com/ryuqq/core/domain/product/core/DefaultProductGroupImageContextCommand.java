package com.ryuqq.core.domain.product.core;

import java.util.List;

record DefaultProductGroupImageContextCommand(

	List<? extends ProductGroupImageCommand> productGroupImageCommands

) implements ProductGroupImageContextCommand {

	@Override
	public ProductGroupImageContextCommand assignProductGroupId(long productGroupId) {
		List<ProductGroupImageCommand> assignedProductGroupImages = productGroupImageCommands.stream()
			.map(p -> p.assignProductGroupId(productGroupId))
			.toList();

		return new DefaultProductGroupImageContextCommand(assignedProductGroupImages);
	}
}
