package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.OptionName;

record DefaultOptionContextCommand(
	long productId,
	long optionGroupId,
	long optionDetailId,
	OptionName optionName,
	String optionValue,
	boolean deleted

) implements OptionContextCommand {

	@Override
	public String optionNameValue() {
		return optionName + " " + optionDetailId;
	}

	@Override
	public OptionContextCommand assignedOptionGroupId(long optionGroupId) {
		return new DefaultOptionContextCommand(productId, optionGroupId, optionDetailId, optionName, optionValue, deleted);
	}

	@Override
	public OptionContextCommand assignedOptionDetailId(long optionDetailId) {
		return new DefaultOptionContextCommand(productId, optionGroupId, optionDetailId, optionName, optionValue, deleted);
	}

}
