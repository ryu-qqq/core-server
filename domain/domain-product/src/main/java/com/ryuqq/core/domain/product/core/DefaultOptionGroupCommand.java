package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.OptionName;

public record DefaultOptionGroupCommand(
	OptionName optionName
) implements OptionGroupCommand {
}
