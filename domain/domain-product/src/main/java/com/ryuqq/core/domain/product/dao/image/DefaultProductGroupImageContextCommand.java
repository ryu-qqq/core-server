package com.ryuqq.core.domain.product.dao.image;

import java.util.List;

public record DefaultProductGroupImageContextCommand(

	List<? extends ProductGroupImageCommand> productGroupImageCommands

) implements ProductGroupImageContextCommand {

	@Override
	public ProductGroupImageContextCommand assignProductGroupId(long productGroupId) {
		List<ProductGroupImageCommand> assignedProductGroupImages = productGroupImageCommands.stream()
			.map(p -> p.assignProductGroupId(productGroupId))
			.toList();

		return new DefaultProductGroupImageContextCommand(assignedProductGroupImages);
	}

	@Override
	public List<? extends ProductGroupImageCommand> getInsertProductGroupImageCommands() {
		return productGroupImageCommands.stream()
			.filter(p -> p.id() == 0)
			.toList();
	}

	@Override
	public List<? extends ProductGroupImageCommand> getUpdateProductGroupImageCommands() {
		return productGroupImageCommands.stream()
			.filter(p -> p.id() != 0)
			.toList();
	}
}
