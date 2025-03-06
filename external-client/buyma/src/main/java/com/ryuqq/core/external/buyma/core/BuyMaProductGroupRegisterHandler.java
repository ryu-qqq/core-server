package com.ryuqq.core.external.buyma.core;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.buyma.helper.BuyMaResponseFactory;
import com.ryuqq.core.external.buyma.mapper.BuyMaProductMapper;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestDto;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponse;

@Component
public class BuyMaProductGroupRegisterHandler implements UpdateTypeHandler<BuyMaProductInsertResponse> {

	private final BuyMaProductMapper buymaProductMapper;
	private final BuyMaRequestExecutor buyMaRequestExecutor;

	public BuyMaProductGroupRegisterHandler(BuyMaProductMapper buymaProductMapper,
											BuyMaRequestExecutor buyMaRequestExecutor) {
		this.buymaProductMapper = buymaProductMapper;
		this.buyMaRequestExecutor = buyMaRequestExecutor;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return
			SiteName.BUYMA.equals(siteName) && ProductDomainEventType.PRODUCT_GROUP_REGISTER.equals(productDomainEventType)
			|| SiteName.BUYMA.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP.equals(productDomainEventType)
			|| SiteName.BUYMA.equals(siteName) &&  ProductDomainEventType.PRICE.equals(productDomainEventType)
			|| SiteName.BUYMA.equals(siteName) && ProductDomainEventType.STOCK.equals(productDomainEventType);
	}

	@Override
	public CompletableFuture<BuyMaProductInsertResponse> handle(ExternalProductGroup externalProductGroup, ExecutorService executor) {
		BuyMaProductInsertRequestDto requestDto = buymaProductMapper.toInsertRequestDto(externalProductGroup);

		return buyMaRequestExecutor.sendRequestAsync(requestDto, executor)
			.thenApply(responseDto -> BuyMaResponseFactory.createProductResponse(
				externalProductGroup,
				responseDto,
				requestDto.getName(),
				BigDecimal.valueOf(requestDto.getReferencePrice()),
				BigDecimal.valueOf(requestDto.getPrice())
			));
	}

}
