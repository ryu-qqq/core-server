package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Service;

import com.ryuqq.core.events.ProductGroupSyncRequiredEvent;
import com.ryuqq.core.events.RealTimeUpdateEvent;

@Service
public class ProductGroupContextEventHandler {

	private final ProductGroupContextEventPublisher productGroupContextEventPublisher;

	public ProductGroupContextEventHandler(ProductGroupContextEventPublisher productGroupContextEventPublisher) {
		this.productGroupContextEventPublisher = productGroupContextEventPublisher;
	}

	public void handleEvents(long productGroupId, ProductGroupCommand productGroupCommand){
		productGroupContextEventPublisher.publish(
			new ProductGroupSyncRequiredEvent(productGroupCommand.sellerId(), productGroupId,
				productGroupCommand.brandId(), productGroupCommand.categoryId())
		);
	}


	public void handleEvents(long productGroupId, ProductGroupCommand productGroupCommand, UpdateDecision updateDecision){
		if (updateDecision.hasUpdates(true)) {
			updateDecision.getRealTimeUpdates().forEach(event -> {
				productGroupContextEventPublisher.publish(
					new RealTimeUpdateEvent(productGroupCommand.sellerId(), productGroupId, event.productDomainEventType())
				);
			});
		}

		if (updateDecision.hasUpdates(false)) {
			productGroupContextEventPublisher.publish(
				new ProductGroupSyncRequiredEvent(productGroupCommand.sellerId(), productGroupId,
					productGroupCommand.brandId(), productGroupCommand.categoryId())
			);
		}

	}

}
