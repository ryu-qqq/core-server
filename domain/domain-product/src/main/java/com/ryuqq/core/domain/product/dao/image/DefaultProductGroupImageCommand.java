package com.ryuqq.core.domain.product.dao.image;

import com.ryuqq.core.enums.ProductImageType;

public record DefaultProductGroupImageCommand(
	long id,
	long productGroupId,
	ProductImageType productImageType,
	String imageUrl,
	String originUrl,
	int displayOrder,
	boolean deleted
) implements ProductGroupImageCommand {

	@Override
	public ProductGroupImageCommand assignId(long id) {
		return new DefaultProductGroupImageCommand(id, productGroupId, productImageType, imageUrl, originUrl, displayOrder, false);
	}

	@Override
	public ProductGroupImageCommand assignProductGroupId(long productGroupId) {
		return new DefaultProductGroupImageCommand(0, productGroupId, productImageType, imageUrl, originUrl, displayOrder, false);
	}

}
