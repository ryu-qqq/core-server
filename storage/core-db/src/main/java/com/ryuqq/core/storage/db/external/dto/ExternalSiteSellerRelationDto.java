package com.ryuqq.core.storage.db.external.dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalSiteSellerRelationDto {

	private final long sellerId;
	private final String sellerName;
	private final List<ExternalSiteDto> sites;

	@QueryProjection
	public ExternalSiteSellerRelationDto(long sellerId, String sellerName, List<ExternalSiteDto> sites) {
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.sites = sites;
	}

	public long getSellerId() {
		return sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public List<ExternalSiteDto> getSites() {
		return sites;
	}

}
