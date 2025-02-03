package com.ryuqq.core.external.sellic.helper;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.external.sellic.SellicOptionContext;
import com.ryuqq.core.external.sellic.request.SellicProductStockUpdateRequestDto;
import com.ryuqq.core.external.sellic.request.SellicProductStockUpdateWrapperDto;

public class SellicRequestFactory {

	public static SellicProductStockUpdateWrapperDto createStockUpdateRequest(
		ExternalProductGroup externalProductGroup,
		SellicOptionContext optionContext
	) {
		List<SellicProductStockUpdateRequestDto> stockUpdateRequests = optionContext.options().stream()
			.map(option -> new SellicProductStockUpdateRequestDto(
				Long.parseLong(externalProductGroup.getExternalProductGroupId()),
				optionContext.optionName1(),
				optionContext.optionName2(),
				List.of(new SellicProductStockUpdateRequestDto.ProductStock(
					option.optionItem1(),
					option.optionItem2(),
					option.additionalPrice(),
					option.quantity()
				))
			))
			.toList();

		return new SellicProductStockUpdateWrapperDto(stockUpdateRequests);
	}

}
