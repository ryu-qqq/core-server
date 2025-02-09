package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.ProductImageType;

record DefaultProductGroupImageCommand(
	long id,
	long productGroupId,
	ProductImageType productImageType,
	String imageUrl,
	String originUrl,
	boolean deleted
) implements ProductGroupImageCommand{

	@Override
	public ProductGroupImageCommand assignId(long id) {
		return new DefaultProductGroupImageCommand(id, productGroupId, productImageType, imageUrl, originUrl, false);
	}

	@Override
	public ProductGroupImageCommand assignProductGroupId(long productGroupId) {
		return new DefaultProductGroupImageCommand(0, productGroupId, productImageType, imageUrl, originUrl, false);
	}

}
