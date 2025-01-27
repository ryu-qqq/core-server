package com.ryuqq.core.storage.db.external.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalBrandDto {

	private final long siteId;
	private final String externalBrandId;
	private final long internalBrandId;

	@QueryProjection
	public ExternalBrandDto(long siteId, String externalBrandId, long internalBrandId) {
		this.siteId = siteId;
		this.externalBrandId = externalBrandId;
		this.internalBrandId = internalBrandId;
	}

	public long getSiteId() {
		return siteId;
	}

	public String getExternalBrandId() {
		return externalBrandId;
	}

	public long getInternalBrandId() {
		return internalBrandId;
	}
}
