package com.ryuqq.core.domain.product.dao.options;

import java.math.BigDecimal;

public interface ProductCommand {
	Long id();
	Long productGroupId();
	boolean soldOut();
	boolean displayed();
	int quantity();
	BigDecimal additionalPrice();
	boolean deleted();

}
