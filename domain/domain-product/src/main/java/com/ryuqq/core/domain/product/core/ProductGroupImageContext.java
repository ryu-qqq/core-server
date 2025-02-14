package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductGroupImageContext {
	Long getProductGroupId();
	List<? extends ProductGroupImage> getImages();
}
