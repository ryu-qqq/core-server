package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductGroupChecker implements UpdateChecker<ProductGroup, ProductGroup> {

	@Override
	public UpdateDecision checkUpdates(long productGroupId, ProductGroup existing, ProductGroup updated) {
		UpdateDecision decision = new UpdateDecision();

		boolean anyChangeDetected = hasUpdates(existing, updated);
		boolean priceChanged = hasPriceUpdates(existing.getPrice(), updated.getPrice());

		if (anyChangeDetected) {
			decision.addUpdate(updated, ProductDomainEventType.PRODUCT_GROUP,false);
		}

		if (priceChanged) {
			decision.addUpdate(updated, ProductDomainEventType.PRICE,true);
		}

		return decision;
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductGroup;
	}

	/**
	 * 일반 필드 변경 여부 확인
	 */
	private boolean hasUpdates(ProductGroup existing, ProductGroup updated) {
		return existing.getBrandId() != updated.getBrandId() ||
			existing.getCategoryId() != updated.getCategoryId() ||
			!Objects.equals(existing.getProductGroupName(), updated.getProductGroupName()) ||
			!Objects.equals(existing.getStyleCode(), updated.getStyleCode()) ||
			existing.getProductCondition() != updated.getProductCondition() ||
			existing.getManagementType() != updated.getManagementType() ||
			existing.getOptionType() != updated.getOptionType() ||
			existing.isSoldOut() != updated.isSoldOut() ||
			existing.isDisplayed() != updated.isDisplayed() ||
			existing.getProductStatus() != updated.getProductStatus() ||
			!Objects.equals(existing.getKeyword(), updated.getKeyword());
	}

	/**
	 * 가격 변경 여부 확인
	 */
	private boolean hasPriceUpdates(DefaultPrice existing, DefaultPrice updated) {
		return !Objects.equals(existing.getRegularPrice(), updated.getRegularPrice()) ||
			!Objects.equals(existing.getCurrentPrice(), updated.getCurrentPrice());
	}

}
