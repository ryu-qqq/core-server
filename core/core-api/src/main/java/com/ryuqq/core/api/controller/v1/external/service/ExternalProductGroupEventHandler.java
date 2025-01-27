package com.ryuqq.core.api.controller.v1.external.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ryuqq.core.events.ProductGroupSyncRequiredEvent;
import com.ryuqq.core.events.ProductGroupSyncUpdateRequiredEvent;
import com.ryuqq.core.events.RealTimeUpdateEvent;

@Component
public class ExternalProductGroupEventHandler {

	private final ExternalProductGroupDomainService externalProductGroupDomainService;

	public ExternalProductGroupEventHandler(ExternalProductGroupDomainService externalProductGroupDomainService) {
		this.externalProductGroupDomainService = externalProductGroupDomainService;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async(value = "asyncThreadPoolTaskExecutor")
	public void handleProductGroupRegisteredEvent(ProductGroupSyncRequiredEvent event) {
		externalProductGroupDomainService.registerExternalProductGroup(
			event.sellerId(), event.productGroupId(), event.brandId(), event.categoryId());
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async(value = "asyncThreadPoolTaskExecutor")
	public void handleBatchUpdateRequiredEvent(ProductGroupSyncUpdateRequiredEvent event) {
		externalProductGroupDomainService.updateExternalProductGroup(
			event.sellerId(), event.productGroupId(), event.brandId(), event.categoryId());
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async(value = "asyncThreadPoolTaskExecutor")
	public void handleRealTimeUpdateEvent(RealTimeUpdateEvent event) {
		externalProductGroupDomainService.requestExternalSite(
			event.sellerId(), event.productGroupId(), event.productDomainEventType()
		);
	}

}
