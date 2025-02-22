package com.ryuqq.core.external.oco.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.oco.OcoProductGroupInsertRequestContext;
import com.ryuqq.core.external.oco.helper.OcoResponseFactory;
import com.ryuqq.core.external.oco.mapper.OcoProductMapper;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;

@Component
public class OcoProductGroupRegisterHandler implements UpdateTypeHandler<OcoProductGroupRequestResponse> {

	private final OcoProductMapper ocoProductMapper;
	private final OcoRequestExecutor ocoRequestExecutor;

	public OcoProductGroupRegisterHandler(OcoProductMapper ocoProductMapper, OcoRequestExecutor ocoRequestExecutor) {
		this.ocoProductMapper = ocoProductMapper;
		this.ocoRequestExecutor = ocoRequestExecutor;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.OCO.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP_REGISTER.equals(productDomainEventType);
	}

	@Override
	public CompletableFuture<OcoProductGroupRequestResponse> handle(ExternalProductGroup externalProductGroup, ExecutorService executor) {
		OcoProductGroupInsertRequestContext requestDtoContext = ocoProductMapper.toInsertRequestDto(externalProductGroup);
		return ocoRequestExecutor.sendRequestAsync(requestDtoContext, executor)
			.thenApply(responseDto -> OcoResponseFactory.createProductInsertResponse(
			externalProductGroup, requestDtoContext, responseDto
		));
	}
}
