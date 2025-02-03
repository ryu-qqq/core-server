package com.ryuqq.core.domain.brand;

import com.ryuqq.core.domain.brand.core.Brand;

public record DefaultBrand(
	long id,
	String brandName,
	String brandNameKr,
	boolean displayed
) implements Brand {
	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getBrandName() {
		return brandName;
	}
}
