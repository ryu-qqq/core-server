package com.ryuqq.core.api.controller.v1.external.request;

import java.util.List;

import com.ryuqq.core.enums.OptionName;

public record ExternalStockUpdateRequestDto(
	long productGroupId,
	List<OptionUpdateRequestEvent> options
) {

	public record OptionUpdateRequestEvent(
		long productId,
		OptionName optionName,
		String optionValue,
		int quantity
	){}

}
