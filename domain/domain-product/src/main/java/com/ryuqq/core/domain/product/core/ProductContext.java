package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductContext {

	Product getProduct();
	List<? extends OptionContext> getOptions();
	String getOptionNameValue();
	boolean isDeleted();

}
