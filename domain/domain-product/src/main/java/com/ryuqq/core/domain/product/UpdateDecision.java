package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.ryuqq.core.enums.ProductDomainEventType;

public class UpdateDecision {

	private final List<UpdateDomain<?>> realTimeUpdates = new ArrayList<>();
	private final List<UpdateDomain<?>> batchUpdates = new ArrayList<>();

	public void addUpdate(Object entity, ProductDomainEventType productDomainEventType, boolean isRealTime) {
		UpdateDomain<?> update = new UpdateDomain<>(entity, productDomainEventType, isRealTime);
		if (isRealTime) {
			realTimeUpdates.add(update);
		} else {
			batchUpdates.add(update);
		}
	}

	public boolean hasUpdates() {
		return !realTimeUpdates.isEmpty() || !batchUpdates.isEmpty();
	}

	public List<UpdateDomain<?>> getRealTimeUpdates() {
		return realTimeUpdates;
	}

	public List<UpdateDomain<?>> getBatchUpdates() {
		return batchUpdates;
	}

}
