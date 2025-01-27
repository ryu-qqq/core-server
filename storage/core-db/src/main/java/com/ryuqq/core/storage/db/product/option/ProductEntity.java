package com.ryuqq.core.storage.db.product.option;

import java.math.BigDecimal;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "PRODUCT")
@Entity
public class ProductEntity extends BaseEntity {

	@Column(name = "PRODUCT_GROUP_ID", nullable = false)
	private long productGroupId;

	@Column(name = "SOLD_OUT", nullable = false)
	private boolean soldOut;

	@Column(name = "DISPLAYED",  nullable = false)
	private boolean displayed;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "ADDITIONAL_PRICE")
	private BigDecimal additionalPrice;

	protected ProductEntity() {}

	public ProductEntity(long productGroupId, boolean soldOut, boolean displayed, int quantity, BigDecimal additionalPrice, boolean deleted) {
		this.productGroupId = productGroupId;
		this.soldOut = soldOut;
		this.displayed = displayed;
		this.quantity = quantity;
		this.additionalPrice = additionalPrice;
		this.deleted = deleted;
	}

	public ProductEntity(long id, long productGroupId, boolean soldOut, boolean displayed, int quantity, BigDecimal additionalPrice, boolean deleted) {
		this.id = id;
		this.productGroupId = productGroupId;
		this.soldOut = soldOut;
		this.displayed = displayed;
		this.quantity = quantity;
		this.additionalPrice = additionalPrice;
		this.deleted = deleted;
	}

	public long getProductGroupId() {
		return productGroupId;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getAdditionalPrice() {
		return additionalPrice;
	}
}
