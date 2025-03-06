package com.ryuqq.core.domain.external;

import com.ryuqq.core.domain.external.core.ExternalBrandMapping;

public record DefaultExternalBrandMapping(
	long siteId,
	String externalBrandId,
	long internalBrandId
) implements ExternalBrandMapping {
	@Override
	public long getSiteId() {
		return siteId;
	}

	@Override
	public long getBrandId() {
		return internalBrandId;
	}

	@Override
	public String getExternalBrandId() {
		return externalBrandId;
	}
}
