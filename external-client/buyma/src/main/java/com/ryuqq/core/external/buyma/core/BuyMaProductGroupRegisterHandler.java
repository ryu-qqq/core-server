package com.ryuqq.core.external.buyma.core;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.buyma.BuyMaClient;
import com.ryuqq.core.external.buyma.helper.BuyMaResponseFactory;
import com.ryuqq.core.external.buyma.mapper.BuyMaProductMapper;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestWrapperDto;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponse;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponseDto;

@Component
public class BuyMaProductGroupRegisterHandler implements UpdateTypeHandler {

	private final BuyMaProductMapper buymaProductMapper;
	private final BuyMaClient buyMaClient;
	private final FeignClientWrapper feignClientWrapper;

	public BuyMaProductGroupRegisterHandler(BuyMaProductMapper buymaProductMapper, BuyMaClient buyMaClient,
											FeignClientWrapper feignClientWrapper) {
		this.buymaProductMapper = buymaProductMapper;
		this.buyMaClient = buyMaClient;
		this.feignClientWrapper = feignClientWrapper;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return
			SiteName.BUYMA.equals(siteName) && ProductDomainEventType.PRODUCT_GROUP_REGISTER.equals(productDomainEventType)
			|| SiteName.BUYMA.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP.equals(productDomainEventType)
			|| SiteName.BUYMA.equals(siteName) &&  ProductDomainEventType.PRICE.equals(productDomainEventType);
	}

	@Override
	public BuyMaProductInsertResponse handle(ExternalProductGroup externalProductGroup) {
		BuyMaProductInsertRequestDto insetRequestDto = buymaProductMapper.toInsetRequestDto(
			externalProductGroup);

		BuyMaProductInsertResponseDto buyMaProductInsertResponseDto = feignClientWrapper.executeFeignCall(
			() -> buyMaClient.insertProduct(new BuyMaProductInsertRequestWrapperDto(insetRequestDto)));

		return BuyMaResponseFactory.createProductResponse(
			externalProductGroup,
			buyMaProductInsertResponseDto,
			insetRequestDto.getName(),
			BigDecimal.valueOf(insetRequestDto.getReferencePrice()),
			BigDecimal.valueOf(insetRequestDto.getPrice())
		);
	}
}
