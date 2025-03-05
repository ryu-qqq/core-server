package com.ryuqq.core.api.controller.v1.product.response;

import java.math.BigDecimal;

import com.ryuqq.core.domain.product.core.Price;

public record PriceResponseDto(
	BigDecimal regularPrice,
	BigDecimal currentPrice,
	BigDecimal salePrice,
	BigDecimal directDiscountPrice,
	int directDiscountRate,
	int discountRate
) {
	public static PriceResponseDto from(Price price) {
		return new PriceResponseDto(
			price.getRegularPrice(),
			price.getCurrentPrice(),
			price.getSalePrice(),
			price.getDirectDiscountPrice(),
			price.getDirectDiscountRate(),
			price.getDiscountRate()
		);
	}
}
