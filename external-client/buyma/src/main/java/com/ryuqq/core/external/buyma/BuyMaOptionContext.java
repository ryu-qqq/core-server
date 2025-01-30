package com.ryuqq.core.external.buyma;

import java.util.List;

import com.ryuqq.core.external.buyma.request.BuyMaOptionInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaVariantInsertRequestDto;

public record BuyMaOptionContext(
	List<BuyMaOptionInsertRequestDto> buyMaOptions,
	List<BuyMaVariantInsertRequestDto> buyMaVariants,
	String optionComment
) {
}
