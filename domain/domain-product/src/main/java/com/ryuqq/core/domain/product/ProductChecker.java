package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.Product;
import com.ryuqq.core.domain.product.dao.options.ProductCommand;

@Component
public class ProductChecker  {
	public boolean checkUpdates(Product existing, ProductCommand updated) {
		return
			existing.isSoldOut() != updated.soldOut() ||
				existing.isDisplayed() != updated.displayed() ||
				existing.getQuantity() != updated.quantity() ||
				!Objects.equals(existing.getAdditionalPrice(), updated.additionalPrice()) ||
				existing.isDeleted() != updated.deleted();
	}

}
