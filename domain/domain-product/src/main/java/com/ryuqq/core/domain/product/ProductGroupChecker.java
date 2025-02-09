package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupCommand;
import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductGroupChecker implements UpdateChecker<ProductGroup, ProductGroupCommand> {

	@Override
	public void checkUpdates(UpdateDecision decision, ProductGroup existing, ProductGroupCommand updated) {

		boolean anyChangeDetected = hasUpdates(existing, updated);
		boolean priceChanged = hasPriceUpdates(existing.getPrice(), updated.getPrice());

		if (anyChangeDetected) {
			decision.addUpdate(updated, ProductDomainEventType.PRODUCT_GROUP,false);
		}

		if (priceChanged) {
			decision.addUpdate(updated, ProductDomainEventType.PRICE,true);
		}

	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof DefaultProductGroup;
	}

	private boolean hasUpdates(ProductGroup existing, ProductGroupCommand updated) {
		return existing.getBrandId() != updated.brandId() ||
			existing.getCategoryId() != updated.categoryId() ||
			!Objects.equals(existing.getProductGroupName(), updated.productGroupName()) ||
			!Objects.equals(existing.getStyleCode(), updated.styleCode()) ||
			existing.getProductCondition() != updated.productCondition() ||
			existing.getManagementType() != updated.managementType() ||
			existing.getOptionType() != updated.optionType() ||
			existing.isSoldOut() != updated.soldOut() ||
			existing.isDisplayed() != updated.displayed() ||
			existing.getProductStatus() != updated.productStatus() ||
			!Objects.equals(existing.getKeyword(), updated.keyword());
	}


	private boolean hasPriceUpdates(Price existing, Price updated) {
		return !Objects.equals(existing.getRegularPrice(), updated.getRegularPrice()) ||
			!Objects.equals(existing.getCurrentPrice(), updated.getCurrentPrice());
	}

}
