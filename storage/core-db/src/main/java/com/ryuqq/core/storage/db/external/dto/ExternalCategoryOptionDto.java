package com.ryuqq.core.storage.db.external.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalCategoryOptionDto {

	private final long siteId;
	private final String externalCategoryId;
	private final long optionGroupId;
	private final long optionId;
	private final String optionValue;

	@QueryProjection
	public ExternalCategoryOptionDto(long siteId, String externalCategoryId, long optionGroupId, long optionId,
									 String optionValue) {
		this.siteId = siteId;
		this.externalCategoryId = externalCategoryId;
		this.optionGroupId = optionGroupId;
		this.optionId = optionId;
		this.optionValue = optionValue;
	}

	public long getSiteId() {
		return siteId;
	}

	public String getExternalCategoryId() {
		return externalCategoryId;
	}

	public long getOptionGroupId() {
		return optionGroupId;
	}

	public long getOptionId() {
		return optionId;
	}

	public String getOptionValue() {
		return optionValue;
	}
}
