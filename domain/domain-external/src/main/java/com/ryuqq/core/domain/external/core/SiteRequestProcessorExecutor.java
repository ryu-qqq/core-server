package com.ryuqq.core.domain.external.core;


import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.MdcContextPropagatingExecutorService;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.ProductDomainEventType;

@Service
public class SiteRequestProcessorExecutor {


	private final MdcContextPropagatingExecutorService executorService;
	private final SiteRequestQueueManager queueManager;
	private final ExternalProductRequestProcessorProvider processorProvider;
	private final SiteRequestProcessResponseHandler responseHandler;

	public SiteRequestProcessorExecutor(
										MdcContextPropagatingExecutorService executorService,
										SiteRequestQueueManager queueManager,
										ExternalProductRequestProcessorProvider processorProvider,
										SiteRequestProcessResponseHandler responseHandler) {
		this.executorService = executorService;
		this.queueManager = queueManager;
		this.processorProvider = processorProvider;
		this.responseHandler = responseHandler;
	}

	public void processRequests(ExternalProductGroup externalProductGroup, ProductDomainEventType productDomainEventType) {
		try {
			SiteRequestProcessor processor = processorProvider.getProcessor(externalProductGroup.getSiteName());
			ExternalMallProductGroupRequestResponse response = processor.process(productDomainEventType, externalProductGroup);
			responseHandler.handleResponse(productDomainEventType, externalProductGroup, response);
		} catch (Exception e) {
			responseHandler.handleFailure(externalProductGroup, e);
		}



			// executorService.submit(() -> {
			// 	try {
			// 		SiteRequestProcessor processor = processorProvider.getProcessor(externalProductGroup.getSiteName());
			// 		ExternalMallProductGroupRequestResponse response = processor.process(productDomainEventType, externalProductGroup);
			// 		responseHandler.handleResponse(productDomainEventType, externalProductGroup, response);
			// 	} catch (Exception e) {
			// 		responseHandler.handleFailure(externalProductGroup, e);
			// 	}
			// });
	}

}
