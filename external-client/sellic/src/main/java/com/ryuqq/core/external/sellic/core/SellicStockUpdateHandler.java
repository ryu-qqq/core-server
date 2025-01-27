package com.ryuqq.core.external.sellic.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.sellic.SellicClient;
import com.ryuqq.core.external.sellic.SellicOptionContext;
import com.ryuqq.core.external.sellic.SellicResponseHandler;
import com.ryuqq.core.external.sellic.helper.SellicRequestFactory;
import com.ryuqq.core.external.sellic.helper.SellicResponseFactory;
import com.ryuqq.core.external.sellic.mapper.SellicOptionConverter;
import com.ryuqq.core.external.sellic.request.SellicProductStockUpdateWrapperDto;
import com.ryuqq.core.external.sellic.response.SellicResponse;

@Component
public class SellicStockUpdateHandler implements UpdateTypeHandler {

	private final SellicOptionConverter sellicOptionConverter;
	private final SellicClient sellicClient;
	private final FeignClientWrapper feignClientWrapper;
	private final SellicResponseHandler sellicResponseHandler;

	public SellicStockUpdateHandler(SellicOptionConverter sellicOptionConverter, SellicClient sellicClient, FeignClientWrapper feignClientWrapper,
									SellicResponseHandler sellicResponseHandler) {
		this.sellicOptionConverter = sellicOptionConverter;
		this.sellicClient = sellicClient;
		this.feignClientWrapper = feignClientWrapper;
		this.sellicResponseHandler = sellicResponseHandler;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.SELLIC.equals(siteName) && ProductDomainEventType.STOCK.equals(productDomainEventType);
	}

	@Override
	public ExternalMallProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup) {
		SellicOptionContext sellicOptionContext = sellicOptionConverter.generateOptionContext(
			externalProductGroup.getProductGroupId());


		SellicProductStockUpdateWrapperDto stockUpdateRequest = SellicRequestFactory.createStockUpdateRequest(
			externalProductGroup, sellicOptionContext
		);

		SellicResponse response = feignClientWrapper.executeFeignCall(() -> sellicClient.updateStock(stockUpdateRequest));
		SellicResponse sellicResponse = sellicResponseHandler.handleResponse(response);

		return SellicResponseFactory.createProductResponse(
			externalProductGroup, sellicResponse,
			externalProductGroup.getProductName(),
			externalProductGroup.getRegularPrice(),
			externalProductGroup.getCurrentPrice()
		);

	}
}
