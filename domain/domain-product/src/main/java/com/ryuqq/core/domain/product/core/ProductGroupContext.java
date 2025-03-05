package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.domain.product.ProductGroupImageContext;

public interface ProductGroupContext {
	long getId();
	ProductGroup getProductGroup();
	ProductDelivery getProductDelivery();
	ProductNotice getProductNotice();
	ProductDetailDescription getProductDetailDescription();
	ProductGroupImageContext getProductGroupImageContext();
	ProductOptionContext getProductOptionContext();

}
