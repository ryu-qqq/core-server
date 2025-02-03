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
import com.ryuqq.core.external.sellic.SellicResponseHandler;
import com.ryuqq.core.external.sellic.helper.SellicResponseFactory;
import com.ryuqq.core.external.sellic.mapper.SellicProductMapper;
import com.ryuqq.core.external.sellic.request.SellicProductInsertRequestDto;
import com.ryuqq.core.external.sellic.response.SellicResponse;

@Component
public class SellicProductGroupUpdateHandler implements UpdateTypeHandler {

	private final SellicProductMapper sellicProductMapper;
	private final SellicClient sellicClient;
	private final FeignClientWrapper feignClientWrapper;
	private final SellicResponseHandler sellicResponseHandler;

	public SellicProductGroupUpdateHandler(SellicProductMapper sellicProductMapper, SellicClient sellicClient,
										   FeignClientWrapper feignClientWrapper,
										   SellicResponseHandler sellicResponseHandler) {
		this.sellicProductMapper = sellicProductMapper;
		this.sellicClient = sellicClient;
		this.feignClientWrapper = feignClientWrapper;
		this.sellicResponseHandler = sellicResponseHandler;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.SELLIC.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP.equals(productDomainEventType);
	}

	@Override
	public ExternalMallProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup) {
		SellicProductInsertRequestDto requestDto = sellicProductMapper.toRequestDto(externalProductGroup);
		SellicResponse response = feignClientWrapper.executeFeignCall(() -> sellicClient.updateProduct(requestDto));
		SellicResponse sellicResponse = sellicResponseHandler.handleResponse(response);

		return SellicResponseFactory.createProductResponse(
			externalProductGroup, sellicResponse,
			requestDto.productGroupName(),
			BigDecimal.valueOf(requestDto.regularPrice()),
			BigDecimal.valueOf(requestDto.currentPrice())
		);
	}

}
