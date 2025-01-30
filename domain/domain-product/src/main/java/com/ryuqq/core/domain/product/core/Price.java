package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

public interface Price {
	BigDecimal getRegularPrice();
	BigDecimal getCurrentPrice();
	BigDecimal getSalePrice();
	BigDecimal getDirectDiscountPrice();
	int getDirectDiscountRate();
	int getDiscountRate();
}
