package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductOptionCommand {
	ProductCommand productCommand();
	boolean deleted();
	String getOptionNameValue();
	List<? extends OptionContextCommand> optionContextCommands();

	static ProductOptionCommand of(ProductCommand productCommand, boolean deleted, List<? extends OptionContextCommand> productOptionCommands){
		return new DefaultProductOptionCommand(productCommand, deleted, productOptionCommands);
	}

	ProductOptionCommand assignId(long id);
	ProductOptionCommand assignProductGroupId(long productGroupId);
	ProductOptionCommand assignProductOptionCommand(List<? extends OptionContextCommand> productOptionCommands);

}
