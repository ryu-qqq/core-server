package com.ryuqq.core.external.sellic.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.sellic.helper.SellicResponseFactory;
import com.ryuqq.core.external.sellic.mapper.SellicProductMapper;
import com.ryuqq.core.external.sellic.request.SellicProductInsertRequestDto;

@Component
public class SellicProductGroupUpdateHandler implements UpdateTypeHandler<ExternalMallProductGroupRequestResponse> {

	private final SellicProductMapper sellicProductMapper;
	private final SellicUpdateRequestExecutor sellicUpdateRequestExecutor;

	public SellicProductGroupUpdateHandler(SellicProductMapper sellicProductMapper,
										   SellicUpdateRequestExecutor sellicUpdateRequestExecutor) {
		this.sellicProductMapper = sellicProductMapper;
		this.sellicUpdateRequestExecutor = sellicUpdateRequestExecutor;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.SELLIC.equals(siteName)
			&& ProductDomainEventType.PRODUCT_GROUP.equals(productDomainEventType)
			|| SiteName.SELLIC.equals(siteName)
			&& ProductDomainEventType.STOCK.equals(productDomainEventType)
			|| SiteName.SELLIC.equals(siteName) && ProductDomainEventType.PRICE.equals(productDomainEventType);
	}

	@Override
	public CompletableFuture<ExternalMallProductGroupRequestResponse> handle(ExternalProductGroup externalProductGroup, ExecutorService executorService) {
		SellicProductInsertRequestDto requestDto = sellicProductMapper.toUpdateRequestDto(externalProductGroup);
		return sellicUpdateRequestExecutor.sendRequestAsync(requestDto, executorService)
			.thenApply(responseDto -> SellicResponseFactory.createProductResponse(
				externalProductGroup, responseDto,
				requestDto.productGroupName(),
				requestDto.regularPrice(),
				requestDto.currentPrice()
			));
	}

}
