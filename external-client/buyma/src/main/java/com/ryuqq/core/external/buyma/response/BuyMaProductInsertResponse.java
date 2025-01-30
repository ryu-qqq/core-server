package com.ryuqq.core.external.buyma.response;

import java.math.BigDecimal;

import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.enums.SyncStatus;

public record BuyMaProductInsertResponse(
	long siteId,
	long productGroupId,
	String externalProductGroupId,
	SyncStatus syncStatus,
	String productGroupName,
	BigDecimal regularPrice,
	BigDecimal currentPrice,
	boolean fixedPrice
) implements ExternalMallProductGroupRequestResponse {

	@Override
	public long getSiteId() {
		return siteId;
	}

	@Override
	public long getProductGroupId() {
		return productGroupId;
	}

	@Override
	public String getExternalProductGroupId() {
		return externalProductGroupId;
	}

	@Override
	public SyncStatus getSyncStatus() {
		return syncStatus;
	}

	@Override
	public String getProductGroupName() {
		return productGroupName;
	}

	@Override
	public BigDecimal getRegularPrice() {
		return regularPrice;
	}

	@Override
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
}
