package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;
import java.util.Objects;

public class DefaultProduct implements Product {

	private final Long id;
	private final Long productGroupId;
	private final boolean soldOut;
	private final boolean displayed;
	private final int quantity;
	private final BigDecimal additionalPrice;
	private final boolean deleted;


	public DefaultProduct(Long id, Long productGroupId, boolean soldOut, boolean displayed, int quantity,
						   BigDecimal additionalPrice, boolean deleted) {
		this.id = id;
		this.productGroupId = productGroupId;
		this.soldOut = soldOut;
		this.displayed = displayed;
		this.quantity = quantity;
		this.additionalPrice = additionalPrice;
		this.deleted = deleted;
	}


	public DefaultProduct assignIdAndProductGroupId(Long id, Long productGroupId) {
		return new DefaultProduct(id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}


	public DefaultProduct assignProductGroupId(Long productGroupId) {
		return new DefaultProduct(this.id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	public Long getId() {
		return id;
	}

	public Long getProductGroupId() {
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

	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultProduct defaultProduct = (DefaultProduct) object;
		return soldOut
			== defaultProduct.soldOut
			&& displayed
			== defaultProduct.displayed
			&& quantity
			== defaultProduct.quantity
			&& deleted
			== defaultProduct.deleted
			&& Objects.equals(id, defaultProduct.id)
			&& Objects.equals(productGroupId, defaultProduct.productGroupId)
			&& Objects.equals(additionalPrice, defaultProduct.additionalPrice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	@Override
	public String toString() {
		return "Product{"
			+
			"id="
			+ id
			+
			", productGroupId="
			+ productGroupId
			+
			", soldOut="
			+ soldOut
			+
			", displayed="
			+ displayed
			+
			", quantity="
			+ quantity
			+
			", additionalPrice="
			+ additionalPrice
			+
			", deleted="
			+ deleted
			+
			'}';
	}
}
