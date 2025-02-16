package com.ryuqq.core.domain.product.dao.options.detail;

public record DefaultOptionDetailCommand(
	long optionGroupId,
	String optionValue
) implements OptionDetailCommand{

}
