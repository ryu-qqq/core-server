package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class ProductChecker  {
	public boolean checkUpdates(Product existing, Product updated) {
		return
			existing.isSoldOut() != updated.isSoldOut() ||
				existing.isDisplayed() != updated.isDisplayed() ||
				existing.getQuantity() != updated.getQuantity() ||
				!Objects.equals(existing.getAdditionalPrice(), updated.getAdditionalPrice()) ||
				existing.isDeleted() != updated.isDeleted();
	}

}
