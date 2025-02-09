package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.OptionName;

public interface OptionGroupCommand {
	OptionName optionName();

	static OptionGroupCommand of(OptionName optionName){
		return new DefaultOptionGroupCommand(optionName);
	}

}
