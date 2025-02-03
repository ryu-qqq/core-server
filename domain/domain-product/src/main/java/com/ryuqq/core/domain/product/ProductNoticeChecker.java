package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductNoticeChecker implements UpdateChecker<ProductNotice, ProductNotice> {

	@Override
	public UpdateDecision checkUpdates(ProductNotice existing, ProductNotice updated) {
		UpdateDecision decision = new UpdateDecision();

		boolean anyChangeDetected = hasUpdates(existing, updated);

		if (anyChangeDetected) {
			decision.addUpdate(updated.assignProductGroupId(existing.getProductGroupId()), ProductDomainEventType.NOTICE,false);
		}

		return decision;
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductNotice;
	}

	private boolean hasUpdates(ProductNotice existing, ProductNotice updated) {
		return !Objects.equals(existing.getMaterial(), updated.getMaterial()) ||
			!Objects.equals(existing.getColor(), updated.getColor()) ||
			!Objects.equals(existing.getSize(), updated.getSize()) ||
			!Objects.equals(existing.getMaker(), updated.getMaker()) ||
			existing.getOrigin() != updated.getOrigin() ||
			!Objects.equals(existing.getWashingMethod(), updated.getWashingMethod()) ||
			!Objects.equals(existing.getYearMonth(), updated.getYearMonth()) ||
			!Objects.equals(existing.getAssuranceStandard(), updated.getAssuranceStandard()) ||
			!Objects.equals(existing.getAsPhone(), updated.getAsPhone());
	}


}
