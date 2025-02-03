package com.ryuqq.core.external.sellic;

import java.util.List;

import com.ryuqq.core.external.sellic.request.SellicProductOptionInsertRequestDto;

public record SellicOptionContext(
	List<SellicProductOptionInsertRequestDto> options,
	String optionName1,
	String optionName2
) {
}
