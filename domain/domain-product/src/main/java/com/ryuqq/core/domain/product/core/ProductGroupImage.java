package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.ProductImageType;

public interface ProductGroupImage {
	long getId();
	long getProductGroupId();
	ProductImageType getProductImageType();
	String getOriginUrl();
	String getImageUrl();
	int displayOrder();
	boolean isDeleted();
}
