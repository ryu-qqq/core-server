package com.ryuqq.core.domain.product.dao.image;

import com.ryuqq.core.enums.ProductImageType;

public interface ProductGroupImageCommand {
	long id();
	long productGroupId();
	ProductImageType productImageType();
	String imageUrl();
	String originUrl();
	int displayOrder();
	boolean deleted();


	static ProductGroupImageCommand of(long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, int displayOrder) {
		return new DefaultProductGroupImageCommand(
			0, productGroupId, productImageType, imageUrl, originUrl, displayOrder, false
		);
	}

	static ProductGroupImageCommand of(long id, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, int displayOrder, boolean deleted) {
		return new DefaultProductGroupImageCommand(
			id, productGroupId, productImageType, imageUrl, originUrl, displayOrder, deleted
		);
	}

	ProductGroupImageCommand assignId(long id);
	ProductGroupImageCommand assignProductGroupId(long productGroupId);


}
