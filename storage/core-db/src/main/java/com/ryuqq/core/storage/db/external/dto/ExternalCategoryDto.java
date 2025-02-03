package com.ryuqq.core.storage.db.external.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalCategoryDto {

	private final long siteId;
	private final String externalCategoryId;
	private final String externalExtraCategoryId;
	private final String description;
	private final long internalCategoryId;

	@QueryProjection
	public ExternalCategoryDto(long siteId, String externalCategoryId, String externalExtraCategoryId,
							   String description,
							   long internalCategoryId) {
		this.siteId = siteId;
		this.externalCategoryId = externalCategoryId;
		this.externalExtraCategoryId = externalExtraCategoryId;
		this.description = description;
		this.internalCategoryId = internalCategoryId;
	}

	public long getSiteId() {
		return siteId;
	}

	public String getExternalCategoryId() {
		return externalCategoryId;
	}

	public String getExternalExtraCategoryId() {
		return externalExtraCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public long getInternalCategoryId() {
		return internalCategoryId;
	}
}
