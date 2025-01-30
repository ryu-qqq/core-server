package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.ProductImageType;

public interface ItemImage {
	ProductImageType getProductImageType();
	String getImageUrl();
}
