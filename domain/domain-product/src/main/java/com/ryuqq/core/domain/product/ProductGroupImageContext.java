package com.ryuqq.core.domain.product;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductGroupImage;

public interface ProductGroupImageContext {
	Long getProductGroupId();
	List<? extends ProductGroupImage> getImages();
}
