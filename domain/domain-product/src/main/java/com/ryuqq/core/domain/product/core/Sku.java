package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;
import java.util.List;

public interface Sku {
	Long getId();
	Long getProductGroupId();
	int getQuantity();
	boolean isSoldOut();
	boolean isDisplayed();
	BigDecimal getAdditionalPrice();
	List<? extends Variant> getVariants();

}
