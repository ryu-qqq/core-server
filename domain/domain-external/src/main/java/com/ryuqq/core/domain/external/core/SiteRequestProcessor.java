package com.ryuqq.core.domain.external.core;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;

public interface SiteRequestProcessor {

	boolean supportsSite(SiteName siteName);

	default CompletableFuture<ExternalMallProductGroupRequestResponse> process(
		ProductDomainEventType productDomainEventType,
		ExternalProductGroup externalProductGroup,
		ExecutorService executor,
		List<UpdateTypeHandler<? extends ExternalMallProductGroupRequestResponse>> updateHandlers
	) {
		UpdateTypeHandler<? extends ExternalMallProductGroupRequestResponse> handler = updateHandlers.stream()
			.filter(h -> h.supports(getSupportedSite(), productDomainEventType))
			.findFirst()
			.orElseThrow(() -> new DomainException(ErrorType.UNEXPECTED_ERROR,
				"No handler found for update type: " + productDomainEventType));

		@SuppressWarnings("unchecked")
		UpdateTypeHandler<ExternalMallProductGroupRequestResponse> castedHandler =
			(UpdateTypeHandler<ExternalMallProductGroupRequestResponse>) handler;

		return castedHandler.handle(externalProductGroup, executor);
	}

	SiteName getSupportedSite();


}
