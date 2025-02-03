package com.ryuqq.core.domain.product.dao.options.detail;

public record CreateOptionDetailCommand(
	long optionGroupId,
	String optionValue
) implements OptionDetailCommand {

}
