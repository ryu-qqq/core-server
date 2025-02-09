package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

public interface ProductCommand {
	long id();
	long productGroupId();
	boolean soldOut();
	boolean displayed();
	int quantity();
	BigDecimal additionalPrice();
	boolean deleted();

	static ProductCommand of(long id, long productGroupId, boolean soldOut, boolean displayed, int quantity,
								   BigDecimal additionalPrice, boolean deleted){
		return new DefaultProductCommand(id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	ProductCommand assignId(long id);
	ProductCommand assignProductGroupId(long productGroupId);


}
