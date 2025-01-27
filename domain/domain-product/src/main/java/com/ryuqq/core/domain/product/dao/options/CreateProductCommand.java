package com.ryuqq.core.domain.product.dao.options;

import java.math.BigDecimal;

public record CreateProductCommand(
	Long productGroupId,
	boolean soldOut,
	boolean displayed,
	int quantity,
	BigDecimal additionalPrice,
	boolean deleted
) implements ProductCommand{

	@Override
	public Long id() {
		return null;
	}
}
