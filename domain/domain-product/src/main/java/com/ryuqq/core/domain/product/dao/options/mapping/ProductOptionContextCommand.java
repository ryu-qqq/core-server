package com.ryuqq.core.domain.product.dao.options.mapping;

import java.util.List;

import com.ryuqq.core.enums.OptionType;

public interface ProductOptionContextCommand {
	long productGroupId();
	OptionType optionType();
	List<? extends ProductOptionCommand> productCommands();

	static ProductOptionContextCommand of(long productGroupId, OptionType optionType, List<? extends ProductOptionCommand> productCommands) {
		return new DefaultProductOptionContextCommand(productGroupId, optionType, productCommands);
	}

	ProductOptionContextCommand assignProductGroupId(long productGroupId);

}
