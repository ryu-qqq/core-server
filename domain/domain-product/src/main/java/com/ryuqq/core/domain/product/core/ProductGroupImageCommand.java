package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.ProductImageType;

public interface ProductGroupImageCommand {
	long id();
	long productGroupId();
	ProductImageType productImageType();
	String imageUrl();
	String originUrl();
	boolean deleted();


	static ProductGroupImageCommand of(long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl) {
		return new DefaultProductGroupImageCommand(
			0, productGroupId, productImageType, imageUrl, originUrl, false
		);
	}

	static ProductGroupImageCommand of(long id, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, boolean deleted) {
		return new DefaultProductGroupImageCommand(
			id, productGroupId, productImageType, imageUrl, originUrl, deleted
		);
	}

	ProductGroupImageCommand assignId(long id);
	ProductGroupImageCommand assignProductGroupId(long productGroupId);


}
