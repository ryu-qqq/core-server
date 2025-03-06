package com.ryuqq.core.storage.db.product.group;


import java.math.BigDecimal;

import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "PRODUCT_GROUP")
@Entity
public class ProductGroupEntity extends BaseEntity {

    @Column(name = "SELLER_ID", nullable = false)
    private long sellerId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private long categoryId;

    @Column(name = "BRAND_ID", nullable = false)
    private long brandId;

    @Column(name = "PRODUCT_GROUP_NAME", length = 250, nullable = false)
    private String productGroupName;

    @Column(name = "STYLE_CODE", length = 50)
    private String styleCode;

    @Column(name = "PRODUCT_CONDITION", length = 15, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductCondition productCondition;

    @Column(name = "MANAGEMENT_TYPE", length = 15, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ManagementType managementType;

    @Column(name = "OPTION_TYPE", length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OptionType optionType;

    @Column(name = "REGULAR_PRICE", nullable = false)
    private BigDecimal regularPrice;

    @Column(name = "CURRENT_PRICE", nullable = false)
    private BigDecimal currentPrice;

	@Column(name = "SALE_PRICE", nullable = false)
	private BigDecimal salePrice;

    @Column(name = "DISCOUNT_RATE", nullable = false)
    private int discountRate;

    @Column(name = "SOLD_OUT", nullable = false)
    private boolean soldOut;

    @Column(name = "DISPLAYED", nullable = false)
    private boolean displayed;

    @Column(name = "PRODUCT_STATUS", length = 10,  nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    @Column(name = "KEYWORDS", length =255, nullable = true)
    private String keywords;

    protected ProductGroupEntity() {}

    public ProductGroupEntity(long sellerId, long categoryId, long brandId, String productGroupName, String styleCode, ProductCondition productCondition, ManagementType managementType, OptionType optionType, BigDecimal regularPrice, BigDecimal currentPrice, BigDecimal salePrice, int discountRate, boolean soldOut, boolean displayed, ProductStatus productStatus, String keywords) {
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
    }

    public ProductGroupEntity(long id, long sellerId, long categoryId, long brandId, String productGroupName, String styleCode, ProductCondition productCondition, ManagementType managementType, OptionType optionType, BigDecimal regularPrice, BigDecimal currentPrice, BigDecimal salePrice, int discountRate, boolean soldOut, boolean displayed, ProductStatus productStatus, String keywords) {
        this.id = id;
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
}
