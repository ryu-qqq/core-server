package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

public record DefaultProductCommand(
	long id,
	long productGroupId,
	boolean soldOut,
	boolean displayed,
	int quantity,
	BigDecimal additionalPrice,
	boolean deleted
) implements ProductCommand {

	@Override
	public ProductCommand assignId(long id) {
		return new DefaultProductCommand(id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	@Override
	public ProductCommand assignProductGroupId(long productGroupId) {
		return new DefaultProductCommand(id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

}
