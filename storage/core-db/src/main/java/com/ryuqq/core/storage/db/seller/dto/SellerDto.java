package com.ryuqq.core.storage.db.seller.dto;

import com.querydsl.core.annotations.QueryProjection;

public class SellerDto {

	private final long id;
	private final String sellerName;

	@QueryProjection
	public SellerDto(long id, String sellerName) {
		this.id = id;
		this.sellerName = sellerName;
	}

	public long getId() {
		return id;
	}

	public String getSellerName() {
		return sellerName;
	}
}
