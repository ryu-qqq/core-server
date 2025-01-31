package com.ryuqq.core.external.sellic.core;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.sellic.SellicClient;
import com.ryuqq.core.external.sellic.SellicPrice;
import com.ryuqq.core.external.sellic.SellicResponseHandler;
import com.ryuqq.core.external.sellic.helper.SellicPriceHelper;
import com.ryuqq.core.external.sellic.helper.SellicResponseFactory;
import com.ryuqq.core.external.sellic.request.SellicProductInsertRequestDto;
import com.ryuqq.core.external.sellic.response.SellicResponse;

@Component
public class SellicPriceUpdateHandler  implements UpdateTypeHandler {

	private final SellicClient sellicClient;
	private final FeignClientWrapper feignClientWrapper;
	private final SellicResponseHandler sellicResponseHandler;

	public SellicPriceUpdateHandler(SellicClient sellicClient,
									FeignClientWrapper feignClientWrapper, SellicResponseHandler sellicResponseHandler) {
		this.sellicClient = sellicClient;
		this.feignClientWrapper = feignClientWrapper;
		this.sellicResponseHandler = sellicResponseHandler;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.SELLIC.equals(siteName) && ProductDomainEventType.PRICE.equals(productDomainEventType);
	}

	@Override
	public ExternalMallProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup) {
		SellicPrice sellicPrice = SellicPriceHelper.calculateFinalPrice(externalProductGroup.getRegularPrice(),
			externalProductGroup.getCurrentPrice());

		SellicProductInsertRequestDto sellicProductInsertRequestDto = new SellicProductInsertRequestDto.Builder()
			.productId(Long.parseLong(externalProductGroup.getExternalProductGroupId()))
			.marketPrice(sellicPrice.regularPrice())
			.salePrice(sellicPrice.currentPrice())
			.build();

		SellicResponse response = feignClientWrapper.executeFeignCall(() -> sellicClient.updateProduct(sellicProductInsertRequestDto));
		SellicResponse sellicResponse = sellicResponseHandler.handleResponse(response);

		return SellicResponseFactory.createProductResponse(
			externalProductGroup, sellicResponse,
			externalProductGroup.getProductName(),
			BigDecimal.valueOf(sellicPrice.regularPrice()),
			BigDecimal.valueOf(sellicPrice.currentPrice())
		);
	}



}
