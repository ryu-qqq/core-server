package com.ryuqq.core.external.oco.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.oco.OcoClient;
import com.ryuqq.core.external.oco.OcoRequestResponseHandler;
import com.ryuqq.core.external.oco.helper.OcoProductPriceUpdateFactory;
import com.ryuqq.core.external.oco.helper.OcoResponseFactory;
import com.ryuqq.core.external.oco.request.OcoProductPriceUpdateRequestDto;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;
import com.ryuqq.core.external.oco.response.OcoProductUpdateResponseDto;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Component
public class OcoPriceUpdateHandler  implements UpdateTypeHandler {

	private final OcoClient ocoClient;
	private final FeignClientWrapper feignClientWrapper;
	private final OcoRequestResponseHandler ocoRequestResponseHandler;

	public OcoPriceUpdateHandler(OcoClient ocoClient, FeignClientWrapper feignClientWrapper,
								 OcoRequestResponseHandler ocoRequestResponseHandler) {
		this.ocoClient = ocoClient;
		this.feignClientWrapper = feignClientWrapper;
		this.ocoRequestResponseHandler = ocoRequestResponseHandler;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.OCO.equals(siteName) && ProductDomainEventType.PRICE.equals(productDomainEventType);
	}

	@Override
	public OcoProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup) {
		OcoProductPriceUpdateRequestDto ocoProductPriceUpdateRequestDto = OcoProductPriceUpdateFactory.toOcoProductPriceUpdateRequestDto(
			externalProductGroup);

		OcoResponse<OcoProductUpdateResponseDto> ocoResponse = feignClientWrapper.executeFeignCall(
			() -> ocoClient.updatePrice(ocoProductPriceUpdateRequestDto));
		ocoRequestResponseHandler.handleResponse(ocoResponse);

		return OcoResponseFactory.createPriceUpdateResponse(externalProductGroup, ocoProductPriceUpdateRequestDto);
	}
}
