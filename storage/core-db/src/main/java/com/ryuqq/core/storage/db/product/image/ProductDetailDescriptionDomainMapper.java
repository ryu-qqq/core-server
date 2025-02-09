package com.ryuqq.core.storage.db.product.image;

import com.ryuqq.core.domain.product.DefaultProductDetailDescription;

public class ProductDetailDescriptionDomainMapper {

	public static DefaultProductDetailDescription toProductDetailDescription(ProductDetailDescriptionDto dto) {
		return DefaultProductDetailDescription.create(dto.getProductGroupId(), dto.getDetailDescription());
	}
}
