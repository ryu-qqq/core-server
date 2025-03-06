package com.ryuqq.core.api.controller.v1.brand.response;

public record DefaultBrandContextResponseDto(
	long id,
	String brandName,
	String brandNameKr,
	boolean displayed
) implements BrandContextResponse {}
