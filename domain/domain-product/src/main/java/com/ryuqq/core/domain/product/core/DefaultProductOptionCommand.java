package com.ryuqq.core.domain.product.core;

import java.util.List;
import java.util.stream.Collectors;

public record DefaultProductOptionCommand(
	ProductCommand productCommand,
	boolean deleted,
	List<? extends OptionContextCommand> optionContextCommands

) implements ProductOptionCommand {

	@Override
	public String getOptionNameValue() {
		return optionContextCommands.stream()
			.map(OptionContextCommand::optionNameValue)
			.collect(Collectors.joining(","));
	}

	@Override
	public ProductOptionCommand assignId(long id) {
		ProductCommand assignedId = productCommand.assignId(id);
		return new DefaultProductOptionCommand(assignedId, false, optionContextCommands);
	}

	@Override
	public ProductOptionCommand assignProductGroupId(long productGroupId) {
		ProductCommand assignProductGroupId = productCommand.assignProductGroupId(productGroupId);
		return new DefaultProductOptionCommand(assignProductGroupId, false, optionContextCommands);
	}

	@Override
	public ProductOptionCommand assignProductOptionCommand(List<? extends OptionContextCommand> productOptionCommands) {
		return new DefaultProductOptionCommand(productCommand, false, productOptionCommands);
	}

}
