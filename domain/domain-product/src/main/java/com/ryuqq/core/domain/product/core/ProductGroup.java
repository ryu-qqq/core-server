package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;

public interface ProductGroup {

	Long getId();
	String getProductGroupName();
	long getCategoryId();
	long getBrandId();
	long getSellerId();
	String getStyleCode();
	ProductCondition getProductCondition();
	ManagementType getManagementType();
	OptionType getOptionType();
	Price getPrice();
	boolean isSoldOut();
	boolean isDisplayed();
	ProductStatus getProductStatus();
	String getKeyword();

}
