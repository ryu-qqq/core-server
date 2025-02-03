package com.ryuqq.core.storage.db.external;


import java.math.BigDecimal;

import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_PRODUCT_GROUP")
@Entity
public class ExternalProductGroupEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "EXTERNAL_PRODUCT_GROUP_ID", nullable = true)
    private String externalProductGroupId;

	@Column(name = "BRAND_ID", nullable = false)
	private long brandId;

	@Column(name = "CATEGORY_ID", nullable = false)
	private long categoryId;

	@Column(name = "SELLER_ID", nullable = false)
	private Long sellerId;

    @Column(name = "PRODUCT_NAME", nullable = true)
    private String productName;

    @Column(name = "REGULAR_PRICE", nullable = true)
    private BigDecimal regularPrice;

    @Column(name = "CURRENT_PRICE", nullable = true)
    private BigDecimal currentPrice;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SyncStatus status;

    @Column(name = "FIXED_PRICE", nullable = true)
    private boolean fixedPrice;

    @Column(name = "SOLD_OUT", nullable = true)
    private boolean soldOut;

    @Column(name = "DISPLAYED",  nullable = true)
    private boolean displayed;


    protected ExternalProductGroupEntity() {}

	public ExternalProductGroupEntity(long siteId, long productGroupId, String externalProductGroupId, long brandId,
									  long categoryId, Long sellerId,
									  String productName,
									  BigDecimal regularPrice, BigDecimal currentPrice, SyncStatus status,
									  boolean fixedPrice, boolean soldOut, boolean displayed) {
		this.siteId = siteId;
		this.productGroupId = productGroupId;
		this.externalProductGroupId = externalProductGroupId;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.sellerId = sellerId;
		this.productName = productName;
		this.regularPrice = regularPrice;
		this.currentPrice = currentPrice;
		this.status = status;
		this.fixedPrice = fixedPrice;
		this.soldOut = soldOut;
		this.displayed = displayed;
	}

	public ExternalProductGroupEntity(Long id, long siteId, long productGroupId, String externalProductGroupId,
									  long brandId, long categoryId, Long sellerId,
									  String productName,
									  BigDecimal regularPrice, BigDecimal currentPrice, SyncStatus status,
									  boolean fixedPrice, boolean soldOut, boolean displayed) {

		this.id = id;
		this.siteId = siteId;
		this.productGroupId = productGroupId;
		this.externalProductGroupId = externalProductGroupId;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.sellerId = sellerId;
		this.productName = productName;
		this.regularPrice = regularPrice;
		this.currentPrice = currentPrice;
		this.status = status;
		this.fixedPrice = fixedPrice;
		this.soldOut = soldOut;
		this.displayed = displayed;
	}

	public long getSiteId() {
        return siteId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

	public long getBrandId() {
		return brandId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public String getExternalProductGroupId() {
        return externalProductGroupId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public SyncStatus getStatus() {
        return status;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public boolean isFixedPrice() {
        return fixedPrice;
    }
}
