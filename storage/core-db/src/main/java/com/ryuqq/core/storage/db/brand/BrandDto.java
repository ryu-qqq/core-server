package com.ryuqq.core.storage.db.brand;

import com.querydsl.core.annotations.QueryProjection;

public class BrandDto {

	private final long id;
	private final String brandName;
	private final String brandNameKr;
	private final boolean displayed;

	@QueryProjection
	public BrandDto(long id, String brandName, String brandNameKr, boolean displayed) {
		this.id = id;
		this.brandName = brandName;
		this.brandNameKr = brandNameKr;
		this.displayed = displayed;
	}

	public long getId() {
		return id;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getBrandNameKr() {
		return brandNameKr;
	}

	public boolean isDisplayed() {
		return displayed;
	}
}
