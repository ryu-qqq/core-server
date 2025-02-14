package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.DefaultProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductDetailDescriptionChecker implements
	UpdateChecker<ProductDetailDescription, ProductDetailDescriptionCommand> {

	@Override
	public void checkUpdates(UpdateDecision decision, ProductDetailDescription existing, ProductDetailDescriptionCommand updated) {

		if (hasUpdates(existing, updated)) {
			ProductDetailDescriptionCommand assignedProductGroupIdCommand = updated.assignProductGroupId(
				existing.getProductGroupId());
			decision.addUpdate(assignedProductGroupIdCommand, ProductDomainEventType.IMAGE,false);
		}
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof DefaultProductDetailDescription;
	}

	private boolean hasUpdates(ProductDetailDescription existing, ProductDetailDescriptionCommand updated) {
		return !Objects.equals(existing.getDetailDescription(), updated.detailDescription());
	}

}
