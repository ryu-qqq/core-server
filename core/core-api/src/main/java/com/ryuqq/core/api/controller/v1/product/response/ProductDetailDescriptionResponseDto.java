package com.ryuqq.core.api.controller.v1.product.response;

import com.ryuqq.core.domain.product.core.ProductDetailDescription;

public record ProductDetailDescriptionResponseDto(
	String detailDescription
) {

	public static ProductDetailDescriptionResponseDto from(ProductDetailDescription productDetailDescription) {
		return new ProductDetailDescriptionResponseDto(productDetailDescription.getDetailDescription());
	}

}
