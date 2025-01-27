package com.ryuqq.core.storage.db.external.dto;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.enums.SyncStatus;

public class ExternalProductGroupDto {

	private final long id;
	private final long siteId;
	private final SiteName siteName;
	private final String sellerName;
	private final long productGroupId;
	private final long brandId;
	private final String externalBrandId;
	private final long categoryId;
	private final String externalCategoryId;
	private final String externalProductGroupId;
	private final String productName;
	private final BigDecimal regularPrice;
	private final BigDecimal currentPrice;
	private final SyncStatus status;
	private final boolean fixedPrice;
	private final boolean soldOut;
	private final boolean displayed;

	@QueryProjection
	public ExternalProductGroupDto(long id, long siteId, SiteName siteName, String sellerName, long productGroupId, long brandId,
								   String externalBrandId,
								   long categoryId, String externalCategoryId, String externalProductGroupId,
								   String productName, BigDecimal regularPrice, BigDecimal currentPrice,
								   SyncStatus status,
								   boolean fixedPrice, boolean soldOut, boolean displayed) {
		this.id = id;
		this.siteId = siteId;
		this.siteName = siteName;
		this.sellerName = sellerName;
		this.productGroupId = productGroupId;
		this.brandId = brandId;
		this.externalBrandId = externalBrandId;
		this.categoryId = categoryId;
		this.externalCategoryId = externalCategoryId;
		this.externalProductGroupId = externalProductGroupId;
		this.productName = productName;
		this.regularPrice = regularPrice;
		this.currentPrice = currentPrice;
		this.status = status;
		this.fixedPrice = fixedPrice;
		this.soldOut = soldOut;
		this.displayed = displayed;
	}

	public long getId() {
		return id;
	}

	public long getSiteId() {
		return siteId;
	}

	public SiteName getSiteName() {
		return siteName;
	}

	public long getProductGroupId() {
		return productGroupId;
	}

	public String getExternalProductGroupId() {
		return externalProductGroupId;
	}

	public long getBrandId() {
		return brandId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public String getExternalBrandId() {
		return externalBrandId;
	}

	public String getExternalCategoryId() {
		return externalCategoryId;
	}

	public String getSellerName() {
		return sellerName;
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

	public boolean isFixedPrice() {
		return fixedPrice;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public boolean isDisplayed() {
		return displayed;
	}
}
