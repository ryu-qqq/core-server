package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Component;

@Component
public class DefaultProductGroupContextEventChecker implements ProductGroupContextEventChecker {

	@Override
	public boolean shouldPublishEvent(long sellerId, UpdateDecision decision) {
		return decision.hasUpdates(true);
	}


}
