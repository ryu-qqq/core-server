package com.ryuqq.core.storage.db.external.dto;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalProductDto {

	private final long id;
	private final String externalProductGroupId;
	private final String externalProductId;
	private final long productId;
	private final String optionValue;
	private final int quantity;
	private final BigDecimal additionalPrice;
	private final boolean soldOut;
	private final boolean displayed;

	@QueryProjection
	public ExternalProductDto(long id, String externalProductGroupId, String externalProductId, long productId, String optionValue,
							  int quantity, BigDecimal additionalPrice, boolean soldOut, boolean displayed) {
		this.id = id;
		this.externalProductGroupId = externalProductGroupId;
		this.externalProductId = externalProductId;
		this.productId = productId;
		this.optionValue = optionValue;
		this.quantity = quantity;
		this.additionalPrice = additionalPrice;
		this.soldOut = soldOut;
		this.displayed = displayed;
	}

	public long getId() {
		return id;
	}

	public String getExternalProductGroupId() {
		return externalProductGroupId;
	}

	public String getExternalProductId() {
		return externalProductId;
	}

	public long getProductId() {
		return productId;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getAdditionalPrice() {
		return additionalPrice;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public boolean isDisplayed() {
		return displayed;
	}
}
