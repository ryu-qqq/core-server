package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.DefaultProductDelivery;
import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductDeliveryChecker implements UpdateChecker<ProductDelivery, ProductDeliveryCommand> {

	@Override
	public void checkUpdates(UpdateDecision decision, ProductDelivery existing, ProductDeliveryCommand updated) {

		boolean anyChangeDetected = hasUpdates(existing, updated);

		if (anyChangeDetected) {
			ProductDeliveryCommand assignedProductGroupIdCommand = updated.assignProductGroupId(existing.getProductGroupId());
			decision.addUpdate(assignedProductGroupIdCommand, ProductDomainEventType.DELIVERY, false);
		}
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof DefaultProductDelivery;
	}

	private boolean hasUpdates(ProductDelivery existing, ProductDeliveryCommand updated) {
		return !Objects.equals(existing.getDeliveryArea(), updated.deliveryArea()) ||
			!Objects.equals(existing.getDeliveryFee(), updated.deliveryFee()) ||
			existing.getDeliveryPeriodAverage() != updated.deliveryPeriodAverage() ||
			existing.getReturnMethodDomestic() != updated.returnMethodDomestic() ||
			existing.getReturnCourierDomestic() != updated.returnCourierDomestic() ||
			!Objects.equals(existing.getReturnChargeDomestic(), updated.returnChargeDomestic()) ||
			!Objects.equals(existing.getReturnExchangeAreaDomestic(), updated.returnExchangeAreaDomestic());
	}

}
