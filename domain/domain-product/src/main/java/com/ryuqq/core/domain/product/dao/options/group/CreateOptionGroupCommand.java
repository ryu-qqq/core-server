package com.ryuqq.core.domain.product.dao.options.group;

import com.ryuqq.core.enums.OptionName;

public record CreateOptionGroupCommand(
	OptionName name
) implements OptionGroupCommand {}
