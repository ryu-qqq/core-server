package com.ryuqq.core.storage.db.product.image;

import com.ryuqq.core.domain.product.ProductDetailDescription;

public class ProductDetailDescriptionDomainMapper {

	public static ProductDetailDescription toProductDetailDescription(ProductDetailDescriptionDto dto) {
		return ProductDetailDescription.create(dto.getDetailDescription());
	}
}
