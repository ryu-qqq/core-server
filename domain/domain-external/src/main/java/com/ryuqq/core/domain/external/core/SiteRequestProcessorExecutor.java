package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.MdcContextPropagatingExecutorService;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalSite;
import com.ryuqq.core.enums.ProductDomainEventType;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SiteRequestProcessorExecutor {

	private static final Logger log = LoggerFactory.getLogger(SiteRequestProcessorExecutor.class);

	private final MdcContextPropagatingExecutorService executorService;
	private final SiteRequestQueueManager queueManager;
	private final ExternalProductRequestProcessorProvider processorProvider;
	private final SiteRequestProcessResponseHandler responseHandler;

	public SiteRequestProcessorExecutor(MdcContextPropagatingExecutorService executorService,
										SiteRequestQueueManager queueManager,
										ExternalProductRequestProcessorProvider processorProvider,
										SiteRequestProcessResponseHandler responseHandler) {
		this.executorService = executorService;
		this.queueManager = queueManager;
		this.processorProvider = processorProvider;
		this.responseHandler = responseHandler;
	}

	public void processRequests(ExternalSite externalSite, ProductDomainEventType productDomainEventType) {
		BlockingQueue<ExternalProductGroup> queue = queueManager.getQueue(externalSite.siteId());

		if (queue == null || queue.isEmpty()) {
			return;
		}

		SiteRequestProcessor processor = processorProvider.getProcessor(externalSite);

		while (!queue.isEmpty()) {
			ExternalProductGroup productGroup = queue.poll();
			if (productGroup == null) continue;

			executorService.submit(() -> {
				try {
					ExternalMallProductGroupRequestResponse response = processor.process(productDomainEventType, productGroup);
					responseHandler.handleResponse(productDomainEventType, productGroup, response);
				} catch (Exception e) {
					responseHandler.handleFailure(productGroup, e);
				}
			});
		}
	}

}
