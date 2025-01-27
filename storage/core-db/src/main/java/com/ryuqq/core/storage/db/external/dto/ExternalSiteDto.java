package com.ryuqq.core.storage.db.external.dto;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.SiteName;

public class ExternalSiteDto {

	private final long siteId;
	private final SiteName siteName;

	@QueryProjection
	public ExternalSiteDto(long siteId, SiteName siteName) {
		this.siteId = siteId;
		this.siteName = siteName;
	}

	public long getSiteId() {
		return siteId;
	}

	public SiteName getSiteName() {
		return siteName;
	}
}
