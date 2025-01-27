package com.ryuqq.core.external.oco.response;

import java.math.BigDecimal;
import java.util.List;

import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.external.oco.OcoProductIdMapping;

public record OcoProductGroupRequestResponse(
	long siteId,
	long productGroupId,
	String externalProductGroupId,
	SyncStatus syncStatus,
	String productGroupName,
	BigDecimal regularPrice,
	BigDecimal currentPrice,
	boolean fixedPrice,
	List<OcoProductIdMapping> ocoProductIdMappings
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

	@Override
	public List<? extends ProductIdMapping> getProductIdMappings() {
		return ocoProductIdMappings;
	}

}
