package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductDetailDescriptionChecker implements
	UpdateChecker<ProductDetailDescription, ProductDetailDescription> {

	@Override
	public UpdateDecision checkUpdates(ProductDetailDescription existing, ProductDetailDescription updated) {
		UpdateDecision decision = new UpdateDecision();

		if (hasUpdates(existing, updated)) {
			decision.addUpdate(updated.assignProductGroupId(existing.getProductGroupId()), ProductDomainEventType.IMAGE,false);
		}

		return decision;
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductDetailDescription;
	}

	private boolean hasUpdates(ProductDetailDescription existing, ProductDetailDescription updated) {
		return !Objects.equals(existing.getDetailDescription(), updated.getDetailDescription());
	}

}
