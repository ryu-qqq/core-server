package com.ryuqq.core.domain.product.coreV2;

import java.util.List;

public class CreateProductGroupImageContextCommand implements ProductGroupImageContextCommand {

	private final List<ProductGroupImageCommand> productGroupImageCommands;

	public CreateProductGroupImageContextCommand(List<ProductGroupImageCommand> productGroupImageCommands) {
		this.productGroupImageCommands = productGroupImageCommands;
	}

	@Override
	public List<? extends ProductGroupImageCommand> getProductGroupImageCommands() {
		return productGroupImageCommands;
	}


}
