package com.ryuqq.core.domain.product.core;

import java.util.List;

import com.ryuqq.core.enums.OptionType;

public interface ProductOptionContext {
	long getProductGroupId();
	OptionType getOptionType();
	List<? extends ProductContext> getProducts();

}
