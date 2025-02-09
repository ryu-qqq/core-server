package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.OptionName;

public interface OptionContext {
	Long getProductOptionId();
	Long getOptionGroupId();
	Long getOptionDetailId();
	Long getProductId();
	OptionName getOptionName();
	String getOptionValue();
	String getOptionNameValue();
	boolean isDeleted();

}
