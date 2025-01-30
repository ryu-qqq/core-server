package com.ryuqq.core.external.buyma.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.buyma.BuyMaClient;
import com.ryuqq.core.external.buyma.helper.BuyMaResponseFactory;
import com.ryuqq.core.external.buyma.mapper.BuyMaStockMapper;
import com.ryuqq.core.external.buyma.request.BuyMaProductStockUpdateRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaProductStockUpdateRequestWrapperDto;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponse;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponseDto;

@Component
public class BuyMaStockUpdateHandler implements UpdateTypeHandler {

	private final BuyMaStockMapper buyMaStockMapper;
	private final BuyMaClient buyMaClient;
	private final FeignClientWrapper feignClientWrapper;

	public BuyMaStockUpdateHandler(BuyMaStockMapper buyMaStockMapper, BuyMaClient buyMaClient,
								   FeignClientWrapper feignClientWrapper) {
		this.buyMaStockMapper = buyMaStockMapper;
		this.buyMaClient = buyMaClient;
		this.feignClientWrapper = feignClientWrapper;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.BUYMA.equals(siteName) && ProductDomainEventType.STOCK.equals(productDomainEventType);
	}

	@Override
	public BuyMaProductInsertResponse handle(ExternalProductGroup externalProductGroup) {
		BuyMaProductStockUpdateRequestDto buyMaProductStockUpdateRequestDto = buyMaStockMapper.toBuyMaProductStockUpdateRequestDto(
			externalProductGroup);

		BuyMaProductInsertResponseDto buyMaProductInsertResponseDto = feignClientWrapper.executeFeignCall(
			() -> buyMaClient.updateStock(new BuyMaProductStockUpdateRequestWrapperDto(buyMaProductStockUpdateRequestDto)));

		return BuyMaResponseFactory.createProductResponse(
			externalProductGroup,
			buyMaProductInsertResponseDto,
			externalProductGroup.getProductName(),
			externalProductGroup.getRegularPrice(),
			externalProductGroup.getCurrentPrice()
		);
	}
}
