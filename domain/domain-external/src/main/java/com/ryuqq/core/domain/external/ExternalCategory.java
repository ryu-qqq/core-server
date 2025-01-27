package com.ryuqq.core.domain.external;

import com.ryuqq.core.domain.external.core.ExternalCategoryMapping;

public record ExternalCategory(
	long siteId,
	String externalCategoryId,
	String externalExtraCategoryId,
	String description,
	long internalCategoryId
) implements ExternalCategoryMapping {
	@Override
	public long getSiteId() {
		return siteId;
	}

	@Override
	public long getCategoryId() {
		return internalCategoryId;
	}

	@Override
	public String getExternalCategoryId() {
		return externalCategoryId;
	}

	@Override
	public String getExternalExtraCategoryId() {
		return externalExtraCategoryId;
	}
}
