package com.ryuqq.core.domain.external.core;

import java.math.BigDecimal;
import java.util.List;

import com.ryuqq.core.enums.SyncStatus;

public interface ExternalMallProductGroupRequestResponse {
	long getSiteId();
	long getProductGroupId();
	String getExternalProductGroupId();
	SyncStatus getSyncStatus();
	String getProductGroupName();
	BigDecimal getRegularPrice();
	BigDecimal getCurrentPrice();
	boolean fixedPrice();

	default List<? extends ProductIdMapping> getProductIdMappings() {
		return List.of();
	}

	interface ProductIdMapping {
		long getProductId();
		String getExternalProductId();
		String optionValue();
		int getQuantity();
		BigDecimal getAdditionalPrice();
		boolean soldOut();
		boolean displayed();
		boolean isDeleted();
	}


}
