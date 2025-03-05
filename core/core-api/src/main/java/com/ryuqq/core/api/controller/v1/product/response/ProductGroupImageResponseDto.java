package com.ryuqq.core.api.controller.v1.product.response;

import com.ryuqq.core.domain.product.core.ProductGroupImage;
import com.ryuqq.core.enums.ProductImageType;

public record ProductGroupImageResponseDto(
	long id,
	ProductImageType productImageType,
	String imageUrl,
	String originUrl,
	int displayOrder
) {


	public static ProductGroupImageResponseDto from(ProductGroupImage productGroupImage) {
		return new ProductGroupImageResponseDto(
			productGroupImage.getId(),
			productGroupImage.getProductImageType(),
			productGroupImage.getImageUrl(),
			productGroupImage.getOriginUrl(),
			0
		);
	}
}
