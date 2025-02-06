package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductDeliveryChecker implements UpdateChecker<ProductDelivery, ProductDelivery> {

	@Override
	public UpdateDecision checkUpdates(long productGroupId, ProductDelivery existing, ProductDelivery updated) {
		UpdateDecision decision = new UpdateDecision();

		boolean anyChangeDetected = hasUpdates(existing, updated);

		if (anyChangeDetected) {
			decision.addUpdate(updated.assignProductGroupId(existing.getProductGroupId()), ProductDomainEventType.DELIVERY, false);
		}

		return decision;
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductDelivery;
	}

	private boolean hasUpdates(ProductDelivery existing, ProductDelivery updated) {
		return !Objects.equals(existing.getDeliveryArea(), updated.getDeliveryArea()) ||
			!Objects.equals(existing.getDeliveryFee(), updated.getDeliveryFee()) ||
			existing.getDeliveryPeriodAverage() != updated.getDeliveryPeriodAverage() ||
			existing.getReturnMethodDomestic() != updated.getReturnMethodDomestic() ||
			existing.getReturnCourierDomestic() != updated.getReturnCourierDomestic() ||
			!Objects.equals(existing.getReturnChargeDomestic(), updated.getReturnChargeDomestic()) ||
			!Objects.equals(existing.getReturnExchangeAreaDomestic(), updated.getReturnExchangeAreaDomestic());
	}

}
