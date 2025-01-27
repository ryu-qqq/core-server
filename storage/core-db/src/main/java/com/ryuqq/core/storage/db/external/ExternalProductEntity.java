package com.ryuqq.core.storage.db.external;


import java.math.BigDecimal;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_PRODUCT")
@Entity
public class ExternalProductEntity extends BaseEntity {

    @Column(name = "EXTERNAL_PRODUCT_GROUP_ID", nullable = false)
    private String externalProductGroupId;

    @Column(name = "EXTERNAL_PRODUCT_ID", nullable = false)
    private String externalProductId;

	@Column(name = "PRODUCT_ID", nullable = false)
	private long productId;

    @Column(name = "OPTION_VALUE", nullable = false)
    private String optionValue;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "ADDITIONAL_PRICE", nullable = false)
    private BigDecimal additionalPrice;

    @Column(name = "SOLD_OUT", nullable = false)
    private boolean soldOut;

    @Column(name = "DISPLAYED",  nullable = false)
    private boolean displayed;

    protected ExternalProductEntity() {}

    public ExternalProductEntity(String externalProductGroupId, String externalProductId, long productId, String optionValue, int quantity, BigDecimal additionalPrice, boolean soldOut, boolean displayed) {
        this.externalProductGroupId = externalProductGroupId;
        this.externalProductId = externalProductId;
		this.productId = productId;
		this.optionValue = optionValue;
        this.quantity = quantity;
        this.additionalPrice = additionalPrice;
        this.soldOut = soldOut;
        this.displayed = displayed;
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
