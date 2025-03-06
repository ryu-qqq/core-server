package com.ryuqq.core.api.controller.v1.product.response;

import com.ryuqq.core.domain.brand.core.Brand;

public record ProductBrandResponseDto(
	long id,
	String brandName
) {

	public static ProductBrandResponseDto from(Brand brand){
		return new ProductBrandResponseDto(
			brand.id(),
			brand.brandName()
		);
	}

}
