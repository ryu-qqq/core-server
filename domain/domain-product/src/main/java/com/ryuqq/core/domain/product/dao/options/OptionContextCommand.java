package com.ryuqq.core.domain.product.dao.options;

import com.ryuqq.core.enums.OptionName;

public interface OptionContextCommand {
	long productId();

	long optionGroupId();

	long optionDetailId();

	OptionName optionName();

	String optionValue();

	String optionNameValue();

	boolean deleted();

	static OptionContextCommand of(long productId, long optionGroupId,
								   long optionDetailId, OptionName optionName, String optionValue) {
		return new DefaultOptionContextCommand(productId, optionGroupId, optionDetailId, optionName, optionValue,
			false);
	}

	OptionContextCommand assignedOptionGroupId(long optionGroupId);

	OptionContextCommand assignedOptionDetailId(long optionDetailId);

}
