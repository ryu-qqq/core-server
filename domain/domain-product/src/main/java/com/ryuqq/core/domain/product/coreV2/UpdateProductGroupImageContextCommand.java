package com.ryuqq.core.domain.product.coreV2;

import java.util.List;

public class CreateProductGroupImageCommandContext implements ProductGroupImageCommandContext{

	private List<ProductGroupImageCommand> productGroupImageCommands;

	@Override
	public List<? extends ProductGroupImageCommand> getProductGroupImageCommands() {
		return productGroupImageCommands;
	}


}
