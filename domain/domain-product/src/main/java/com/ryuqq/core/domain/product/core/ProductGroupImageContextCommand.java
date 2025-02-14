package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductGroupImageContextCommand {

	List<? extends ProductGroupImageCommand> productGroupImageCommands();


	static ProductGroupImageContextCommand of(List<? extends ProductGroupImageCommand> productGroupImageCommands) {
		return new DefaultProductGroupImageContextCommand(productGroupImageCommands);
	}

	ProductGroupImageContextCommand assignProductGroupId(long productGroupId);


	List<? extends ProductGroupImageCommand> getInsertProductGroupImageCommands();
	List<? extends ProductGroupImageCommand> getUpdateProductGroupImageCommands();

}
