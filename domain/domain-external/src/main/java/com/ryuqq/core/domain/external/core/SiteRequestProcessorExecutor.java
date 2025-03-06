package com.ryuqq.core.domain.external.core;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.ProductDomainEventType;

@Service
public class SiteRequestProcessorExecutor {

	private final ExternalProductRequestProcessorProvider processorProvider;
	private final List<UpdateTypeHandler<? extends ExternalMallProductGroupRequestResponse>> updateHandlers;
	private final SiteRequestProcessResponseHandler responseHandler;
	private final ExecutorService virtualThreadExecutor;

	public SiteRequestProcessorExecutor(ExternalProductRequestProcessorProvider processorProvider,
										List<UpdateTypeHandler<? extends ExternalMallProductGroupRequestResponse>> updateHandlers,
										SiteRequestProcessResponseHandler responseHandler,
										ExecutorService virtualThreadExecutor) {
		this.processorProvider = processorProvider;
		this.updateHandlers = updateHandlers;
		this.responseHandler = responseHandler;
		this.virtualThreadExecutor = virtualThreadExecutor;
	}


	public void processRequests(ExternalProductGroup externalProductGroup, ProductDomainEventType productDomainEventType) {
		try {
			SiteRequestProcessor processor = processorProvider.getProcessor(externalProductGroup.getSiteName());

			CompletableFuture<ExternalMallProductGroupRequestResponse> futureResponse = processor.process(
				productDomainEventType, externalProductGroup, virtualThreadExecutor, updateHandlers);

			futureResponse
				.thenApply(response -> {
					responseHandler.handleResponse(productDomainEventType, externalProductGroup, response);
					return null;
				})
				.exceptionally(e -> {
					responseHandler.handleFailure(externalProductGroup, e);
					return null;
				});

		} catch (Exception e) {
			responseHandler.handleFailure(externalProductGroup, e);
		}
	}


}
