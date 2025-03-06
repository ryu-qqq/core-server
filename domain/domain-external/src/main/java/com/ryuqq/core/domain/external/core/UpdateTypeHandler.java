package com.ryuqq.core.domain.external.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;

public interface UpdateTypeHandler<T extends ExternalMallProductGroupRequestResponse> {

	boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType);
	CompletableFuture<T> handle(ExternalProductGroup externalProductGroup, ExecutorService executor);

}
