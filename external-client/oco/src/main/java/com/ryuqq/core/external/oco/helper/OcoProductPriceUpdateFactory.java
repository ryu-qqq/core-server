package com.ryuqq.core.external.oco.helper;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.external.oco.OcoPrice;
import com.ryuqq.core.external.oco.request.OcoProductPriceUpdateRequestDto;

public class OcoProductPriceUpdateFactory {

	public static OcoProductPriceUpdateRequestDto toOcoProductPriceUpdateRequestDto(ExternalProductGroup externalProductGroup) {
		OcoPrice ocoPrice = com.ryuqq.core.external.oco.helper.OcoPriceHelper.calculateFinalPrice(externalProductGroup.getRegularPrice(),
			externalProductGroup.getCurrentPrice());

		return new OcoProductPriceUpdateRequestDto(
			Long.parseLong(externalProductGroup.getExternalProductGroupId()),
			new OcoProductPriceUpdateRequestDto.PriceProduct(
				Long.parseLong(externalProductGroup.getExternalProductGroupId()),
				ocoPrice.regularPrice().intValue(), ocoPrice.currentPrice().intValue()
			)
		);
	}

}
