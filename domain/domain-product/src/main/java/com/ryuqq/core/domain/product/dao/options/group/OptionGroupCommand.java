package com.ryuqq.core.domain.product.dao.options.group;

import com.ryuqq.core.enums.OptionName;

public interface OptionGroupCommand {
	OptionName optionName();

	static OptionGroupCommand of(OptionName optionName){
		return new DefaultOptionGroupCommand(optionName);
	}

}
