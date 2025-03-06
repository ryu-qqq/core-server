package com.ryuqq.core.external.oco.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.oco.OcoProductGroupUpdateRequestContext;
import com.ryuqq.core.external.oco.helper.OcoResponseFactory;
import com.ryuqq.core.external.oco.mapper.OcoProductMapper;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;

@Component
public class OcoProductGroupUpdateHandler implements UpdateTypeHandler<OcoProductGroupRequestResponse> {

	private final OcoProductMapper ocoProductMapper;
	private final OcoUpdateRequestExecutor ocoUpdateRequestExecutor;

	public OcoProductGroupUpdateHandler(OcoProductMapper ocoProductMapper,
										OcoUpdateRequestExecutor ocoUpdateRequestExecutor) {
		this.ocoProductMapper = ocoProductMapper;
		this.ocoUpdateRequestExecutor = ocoUpdateRequestExecutor;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.OCO.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP.equals(productDomainEventType)
			|| SiteName.OCO.equals(siteName) && ProductDomainEventType.PRICE.equals(productDomainEventType)
			|| SiteName.OCO.equals(siteName) && ProductDomainEventType.STOCK.equals(productDomainEventType);
	}

	@Override
	public CompletableFuture<OcoProductGroupRequestResponse> handle(ExternalProductGroup externalProductGroup, ExecutorService executor) {
		 OcoProductGroupUpdateRequestContext updateRequestDto= ocoProductMapper.toUpdateRequestDto(
			externalProductGroup);

		return ocoUpdateRequestExecutor.sendRequestAsync(updateRequestDto, executor)
			.thenApply(responseDto -> OcoResponseFactory.createProductUpdateResponse(
				externalProductGroup, updateRequestDto, responseDto
			));
	}

}
