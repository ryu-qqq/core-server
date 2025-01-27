package com.ryuqq.core.domain.product.core;

public interface ProductGroupContextEventChecker {
	boolean shouldPublishEvent(long sellerId, UpdateDecision decision);
}
