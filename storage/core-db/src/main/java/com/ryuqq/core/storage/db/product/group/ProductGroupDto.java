package com.ryuqq.core.storage.db.product.group;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;

public class ProductGroupDto {

    private final long productGroupId;
    private final long sellerId;
    private final long categoryId;
    private final long brandId;
    private final String productGroupName;
    private final String styleCode;
    private final ProductCondition productCondition;
    private final ManagementType managementType;
    private final OptionType optionType;
    private final BigDecimal regularPrice;
    private final BigDecimal currentPrice;
	private final BigDecimal salePrice;
	private final int discountRate;
    private final boolean soldOut;
    private final boolean displayed;
    private final ProductStatus productStatus;
    private final String keywords;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;

	@QueryProjection
	public ProductGroupDto(long productGroupId, long sellerId, long categoryId, long brandId, String productGroupName,
						   String styleCode, ProductCondition productCondition, ManagementType managementType,
						   OptionType optionType, BigDecimal regularPrice, BigDecimal currentPrice, BigDecimal salePrice,
						   int discountRate, boolean soldOut, boolean displayed, ProductStatus productStatus, String keywords,
						   LocalDateTime createAt,
						   LocalDateTime updateAt) {
		this.productGroupId = productGroupId;
		this.sellerId = sellerId;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.productGroupName = productGroupName;
		this.styleCode = styleCode;
		this.productCondition = productCondition;
		this.managementType = managementType;
		this.optionType = optionType;
		this.regularPrice = regularPrice;
		this.currentPrice = currentPrice;
		this.salePrice = salePrice;
		this.discountRate = discountRate;
		this.soldOut = soldOut;
		this.displayed = displayed;
		this.productStatus = productStatus;
		this.keywords = keywords;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public long getProductGroupId() {
		return productGroupId;
	}

	public long getSellerId() {
		return sellerId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public long getBrandId() {
		return brandId;
	}

	public String getProductGroupName() {
		return productGroupName;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public ProductCondition getProductCondition() {
		return productCondition;
	}

	public ManagementType getManagementType() {
		return managementType;
	}

	public OptionType getOptionType() {
		return optionType;
	}

	public BigDecimal getRegularPrice() {
		return regularPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public ProductStatus getProductStatus() {
		return productStatus;
	}

	public String getKeywords() {
		return keywords;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
}
