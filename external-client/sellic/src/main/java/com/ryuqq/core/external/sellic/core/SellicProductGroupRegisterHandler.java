package com.ryuqq.core.external.sellic.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.sellic.helper.SellicResponseFactory;
import com.ryuqq.core.external.sellic.mapper.SellicProductMapper;
import com.ryuqq.core.external.sellic.request.SellicProductInsertRequestDto;
import com.ryuqq.core.external.sellic.response.SellicProductGroupRequestResponse;

@Service
public class SellicProductGroupRegisterHandler  implements UpdateTypeHandler<SellicProductGroupRequestResponse> {

	private final SellicProductMapper sellicProductMapper;
	private final SellicRequestExecutor sellicRequestExecutor;

	public SellicProductGroupRegisterHandler(SellicProductMapper sellicProductMapper,
											 SellicRequestExecutor sellicRequestExecutor) {
		this.sellicProductMapper = sellicProductMapper;
		this.sellicRequestExecutor = sellicRequestExecutor;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.SELLIC.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP_REGISTER.equals(productDomainEventType);
	}

	@Override
	public CompletableFuture<SellicProductGroupRequestResponse> handle(ExternalProductGroup externalProductGroup, ExecutorService executorService) {
		SellicProductInsertRequestDto requestDto = sellicProductMapper.toInsertRequestDto(externalProductGroup);
		return sellicRequestExecutor.sendRequestAsync(requestDto, executorService)
			.thenApply(responseDto -> SellicResponseFactory.createProductResponse(
				externalProductGroup, responseDto,
				requestDto.productGroupName(),
				requestDto.regularPrice(),
				requestDto.currentPrice()
			));
	}

}
