package com.ryuqq.core.api.controller.v1.product.response;

import com.ryuqq.core.domain.product.core.OptionContext;
import com.ryuqq.core.enums.OptionName;

public record OptionResponseDto(
	long optionGroupId,
	long optionDetailId,
	OptionName optionName,
	String optionValue
) {

	public static OptionResponseDto from(OptionContext optionContext) {
		return new OptionResponseDto(
			optionContext.getOptionGroupId(),
			optionContext.getOptionDetailId(),
			optionContext.getOptionName(),
			optionContext.getOptionValue()
		);
	}

}
