package com.ryuqq.core.domain.product.core;

public record DefaultOptionDetailCommand(
	long optionGroupId,
	String optionValue
) implements OptionDetailCommand{

}
