package com.ryuqq.core.api.controller.v1.product.response;

import com.ryuqq.core.domain.product.core.ProductNotice;
import com.ryuqq.core.enums.Origin;

public record ProductNoticeResponseDto(
	String material,
	String color,
	String size,
	String maker,
	Origin origin,
	String washingMethod,
	String yearMonth,
	String assuranceStandard,
	String asPhone
) {

	public static ProductNoticeResponseDto from(ProductNotice productNotice) {
		return new ProductNoticeResponseDto(
			productNotice.getMaterial(),
			productNotice.getColor(),
			productNotice.getSize(),
			productNotice.getMaker(),
			productNotice.getOrigin(),
			productNotice.getWashingMethod(),
			productNotice.getYearMonth(),
			productNotice.getAssuranceStandard(),
			productNotice.getAsPhone()
		);
	}
}
