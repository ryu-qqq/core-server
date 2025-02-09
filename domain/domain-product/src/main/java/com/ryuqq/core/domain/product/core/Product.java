package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

public interface Product {

	Long getId();
	Long getProductGroupId();
	int getQuantity();
	boolean isSoldOut();
	boolean isDisplayed();
	BigDecimal getAdditionalPrice();
	boolean isDeleted();

}
