package com.ryuqq.core.domain.product.dao.image;

import com.ryuqq.core.enums.ProductImageType;

public interface ProductGroupImageCommand {
	Long id();
	Long productGroupId();
	ProductImageType productImageType();
	String imageUrl();
	String originUrl();
	boolean deleted();

}
