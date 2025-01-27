package com.ryuqq.core.external.oco;

import java.math.BigDecimal;

import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;

public record OcoProductIdMapping(
	long productId,
	long productOptionId,
	String optionValue,
	int quantity,
	BigDecimal additionalPrice,
	boolean soldOut,
	boolean displayed,
	boolean deleted


) implements ExternalMallProductGroupRequestResponse.ProductIdMapping {

	@Override
	public long getProductId() {
		return productId;
	}

	@Override
	public String getExternalProductId() {
		return String.valueOf(productOptionId);
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public BigDecimal getAdditionalPrice() {
		return additionalPrice;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}
}
