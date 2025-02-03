package com.ryuqq.core.domain.external;

import java.math.BigDecimal;
import java.util.Objects;

import com.ryuqq.core.domain.external.core.ExternalSku;

public class ExternalProduct implements ExternalSku {

	private final Long id;
	private final String externalProductGroupId;
	private final String externalProductId;
	private final long productId;
	private final String optionValue;
	private final int quantity;
	private final BigDecimal additionalPrice;
	private final boolean soldOut;
	private final boolean displayed;
	private final boolean deleted;

	private ExternalProduct(Long id, String externalProductGroupId, String externalProductId, long productId, String optionValue,
							int quantity, BigDecimal additionalPrice, boolean soldOut, boolean displayed,
							boolean deleted) {
		this.id = id;
		this.externalProductGroupId = externalProductGroupId;
		this.externalProductId = externalProductId;
		this.productId = productId;
		this.optionValue = optionValue;
		this.quantity = quantity;
		this.additionalPrice = additionalPrice;
		this.soldOut = soldOut;
		this.displayed = displayed;
		this.deleted = deleted;
	}

	public static ExternalProduct create(long id, String externalProductGroupId, String externalProductId, long productId, String optionValue,
										 int quantity, BigDecimal additionalPrice, boolean soldOut, boolean displayed){
		return new ExternalProduct(id, externalProductGroupId, externalProductId, productId, optionValue, quantity, additionalPrice, soldOut, displayed, false);
	}

	public static ExternalProduct create(String externalProductGroupId, String externalProductId, long productId, String optionValue,
										 int quantity, BigDecimal additionalPrice, boolean soldOut, boolean displayed, boolean deleted){
		return new ExternalProduct(null, externalProductGroupId, externalProductId, productId, optionValue, quantity, additionalPrice, soldOut, displayed, deleted);
	}

	public String getExternalProductGroupId() {
		return externalProductGroupId;
	}

	public Long getId() {
		return id;
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

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ExternalProduct that = (ExternalProduct) object;
		return quantity
			== that.quantity
			&& soldOut
			== that.soldOut
			&& displayed
			== that.displayed
			&& Objects.equals(id, that.id)
			&& Objects.equals(externalProductGroupId, that.externalProductGroupId)
			&& Objects.equals(externalProductId, that.externalProductId)
			&& Objects.equals(optionValue, that.optionValue)
			&& Objects.equals(additionalPrice, that.additionalPrice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, externalProductGroupId, externalProductId, optionValue, quantity, additionalPrice,
			soldOut,
			displayed);
	}

	@Override
	public String toString() {
		return "ExternalProduct{"
			+
			"id="
			+ id
			+
			", externalProductGroupId='"
			+ externalProductGroupId
			+ '\''
			+
			", externalProductId='"
			+ externalProductId
			+ '\''
			+
			", optionValue='"
			+ optionValue
			+ '\''
			+
			", quantity="
			+ quantity
			+
			", additionalPrice="
			+ additionalPrice
			+
			", soldOut="
			+ soldOut
			+
			", displayed="
			+ displayed
			+
			'}';
	}
}
