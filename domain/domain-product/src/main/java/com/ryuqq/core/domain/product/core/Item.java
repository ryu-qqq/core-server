package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;

public interface Item {
	Long getId();
	String getProductGroupName();
	long getCategoryId();
	long getBrandId();
	String getStyleCode();
	ProductCondition getProductCondition();
	OptionType getOptionType();
	Price getPrice();
	boolean isSoldOut();
	boolean isDisplayed();

}
