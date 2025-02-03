package com.ryuqq.core.storage.db.product.option;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.OptionName;

public class ProductContextDto {

	private final long productGroupId;
	private final long productId;
	private final int quantity;
	private final boolean soldOutYn;
	private final boolean displayYn;
	private final boolean productDeleted;
	private final long productOptionId;
	private final Long optionGroupId;
	private final Long optionDetailId;
	private final OptionName optionName;
	private final String optionValue;
	private final BigDecimal additionalPrice;

	@QueryProjection
	public ProductContextDto(long productGroupId, long productId, int quantity, boolean soldOutYn, boolean displayYn,
							 boolean productDeleted, long productOptionId, Long optionGroupId, Long optionDetailId, OptionName optionName, String optionValue,
							 BigDecimal additionalPrice) {
		this.productGroupId = productGroupId;
		this.productId = productId;
		this.quantity = quantity;
		this.soldOutYn = soldOutYn;
		this.displayYn = displayYn;
		this.productDeleted = productDeleted;
		this.productOptionId = productOptionId;
		this.optionGroupId = optionGroupId;
		this.optionDetailId = optionDetailId;
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.additionalPrice = additionalPrice;
	}

	public long getProductGroupId() {
		return productGroupId;
	}

	public long getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isSoldOutYn() {
		return soldOutYn;
	}

	public boolean isDisplayYn() {
		return displayYn;
	}

	public long getProductOptionId() {
		return productOptionId;
	}

	public Long getOptionGroupId() {
		return optionGroupId;
	}

	public Long getOptionDetailId() {
		return optionDetailId;
	}

	public OptionName getOptionName() {
		return optionName;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public BigDecimal getAdditionalPrice() {
		return additionalPrice;
	}

	public boolean isProductDeleted() {
		return productDeleted;
	}

}
