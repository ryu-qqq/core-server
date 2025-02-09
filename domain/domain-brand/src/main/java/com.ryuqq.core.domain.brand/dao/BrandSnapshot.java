package com.ryuqq.core.domain.brand.dao;

public record BrandSnapshot(
	long id,
	String brandName,
	String brandNameKr,
	boolean displayed
) {}
