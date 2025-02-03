package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.events.ProductGroupSyncRequiredEvent;
import com.ryuqq.core.events.ProductGroupSyncUpdateRequiredEvent;
import com.ryuqq.core.events.RealTimeUpdateEvent;

@Component
public class ProductGroupContextEventHandler {

	private final ProductGroupEventPublisher productGroupEventPublisher;
	private final ProductGroupContextEventChecker productGroupContextEventChecker;

	public ProductGroupContextEventHandler(ProductGroupEventPublisher productGroupEventPublisher,
										   ProductGroupContextEventChecker productGroupContextEventChecker) {
		this.productGroupEventPublisher = productGroupEventPublisher;
		this.productGroupContextEventChecker = productGroupContextEventChecker;
	}

	public void handleEvents(long sellerId, long productGroupId, long brandId, long categoryId) {
		productGroupEventPublisher.publishEvent(
			new ProductGroupSyncRequiredEvent(sellerId, productGroupId, brandId, categoryId)
		);
	}

	public void handleEvents(long sellerId, long productGroupId, long brandId, long categoryId, UpdateDecision decision) {
		if (productGroupContextEventChecker.shouldPublishEvent(sellerId, decision)) {
			decision.getRealTimeUpdates().forEach(event -> {
				productGroupEventPublisher.publishEvent(new RealTimeUpdateEvent(sellerId, productGroupId, event.productDomainEventType()));
			});
		}

		if (decision.hasUpdates(false)) {
			productGroupEventPublisher.publishEvent(
				new ProductGroupSyncUpdateRequiredEvent(sellerId, productGroupId, brandId, categoryId)
			);
		}
	}

}
