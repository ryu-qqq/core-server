package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;

public class DefaultProductGroup implements ProductGroup {

	private final Long id;
	private final long sellerId;
	private final long categoryId;
	private final long brandId;
	private final String productGroupName;
	private final String styleCode;
	private final ProductCondition productCondition;
	private final ManagementType managementType;
	private final OptionType optionType;
	private final DefaultPrice defaultPrice;
	private final boolean soldOut;
	private final boolean displayed;
	private final ProductStatus productStatus;
	private final String keyword;
	private final LocalDateTime createAt;
	private final LocalDateTime updateAt;

	private DefaultProductGroup(Long id, long sellerId, long categoryId, long brandId, String productGroupName,
								String styleCode,
								ProductCondition productCondition, ManagementType managementType, OptionType optionType, DefaultPrice defaultPrice, boolean soldOut,
								boolean displayed, ProductStatus productStatus, String keyword, LocalDateTime createAt,
								LocalDateTime updateAt) {
		this.id = id;
		this.sellerId = sellerId;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.productGroupName = productGroupName;
		this.styleCode = styleCode;
		this.productCondition = productCondition;
		this.managementType = managementType;
		this.optionType = optionType;
		this.defaultPrice = defaultPrice;
		this.soldOut = soldOut;
		this.displayed = displayed;
		this.productStatus = productStatus;
		this.keyword = keyword;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public static DefaultProductGroup create(Long id, long sellerId, long categoryId, long brandId, String productGroupName,
											 String styleCode,
											 ProductCondition productCondition, ManagementType managementType, OptionType optionType,
											 BigDecimal regularPrice, BigDecimal currentPrice, BigDecimal salePrice, boolean soldOut,
											 boolean displayed, ProductStatus productStatus, String keyword, LocalDateTime createAt,
											 LocalDateTime updateAt){
		return new DefaultProductGroup(
			id,
			sellerId,
			categoryId,
			brandId,
			productGroupName,
			styleCode,
			productCondition,
			managementType,
			optionType,
			DefaultPrice.create(regularPrice, currentPrice, salePrice),
			soldOut,
			displayed,
			productStatus,
			keyword,
			createAt,
			updateAt
		);
	}

	public DefaultProductGroup assignId(Long id) {
		return new DefaultProductGroup(id, this.sellerId, this.categoryId, this.brandId,
		this.productGroupName, this.styleCode, this.productCondition, this.managementType, this.optionType,
		this.defaultPrice, this.soldOut , this.displayed, this.productStatus, this.keyword, this.createAt, this.updateAt);
	}

	public Long getId() {
		return id;
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

	public DefaultPrice getPrice() {
		return defaultPrice;
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

	public String getKeyword() {
		return keyword;
	}

	public BigDecimal getRegularPrice(){
		return defaultPrice.getRegularPrice();
	}

	public BigDecimal getCurrentPrice(){
		return defaultPrice.getCurrentPrice();
	}

	public int getDiscountRate(){
		return defaultPrice.getDiscountRate();
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultProductGroup that = (DefaultProductGroup) object;
		return sellerId
			== that.sellerId
			&& categoryId
			== that.categoryId
			&& brandId
			== that.brandId
			&& soldOut
			== that.soldOut
			&& displayed
			== that.displayed
			&& Objects.equals(id, that.id)
			&& Objects.equals(productGroupName, that.productGroupName)
			&& Objects.equals(styleCode, that.styleCode)
			&& productCondition
			== that.productCondition
			&& managementType
			== that.managementType
			&& optionType
			== that.optionType
			&& Objects.equals(defaultPrice, that.defaultPrice)
			&& productStatus
			== that.productStatus
			&& Objects.equals(keyword, that.keyword);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, sellerId, categoryId, brandId, productGroupName, styleCode, productCondition,
			managementType, optionType, defaultPrice, soldOut, displayed, productStatus, keyword, createAt, updateAt);
	}

	@Override
	public String toString() {
		return "DefaultProductGroup{"
			+
			"id="
			+ id
			+
			", sellerId="
			+ sellerId
			+
			", categoryId="
			+ categoryId
			+
			", brandId="
			+ brandId
			+
			", productGroupName='"
			+ productGroupName
			+ '\''
			+
			", styleCode='"
			+ styleCode
			+ '\''
			+
			", productCondition="
			+ productCondition
			+
			", managementType="
			+ managementType
			+
			", optionType="
			+ optionType
			+
			", defaultPrice="
			+ defaultPrice
			+
			", soldOut="
			+ soldOut
			+
			", displayed="
			+ displayed
			+
			", productStatus="
			+ productStatus
			+
			", keyword='"
			+ keyword
			+ '\''
			+
			", createAt="
			+ createAt
			+
			", updateAt="
			+ updateAt
			+
			'}';
	}
}
