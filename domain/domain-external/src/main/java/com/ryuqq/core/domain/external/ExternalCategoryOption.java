package com.ryuqq.core.domain.external;

import com.ryuqq.core.domain.external.core.ExternalCategoryOptionMapping;

public record ExternalCategoryOption(
	long siteId,
	String externalCategoryId,
	long optionGroupId,
	long optionId,
	String optionValue
) implements ExternalCategoryOptionMapping {

	@Override
	public long getSite() {
		return siteId;
	}

	@Override
	public String getExternalCategoryId() {
		return externalCategoryId;
	}

	@Override
	public long getOptionGroupId() {
		return optionGroupId;
	}

	@Override
	public long getOptionId() {
		return optionId;
	}

	@Override
	public String getOptionValue() {
		return optionValue;
	}
}
