package com.ryuqq.core.external.sellic.helper;

import java.math.BigDecimal;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.external.sellic.response.SellicProductGroupRequestResponse;
import com.ryuqq.core.external.sellic.response.SellicResponse;

public class SellicResponseFactory {

	public static SellicProductGroupRequestResponse createProductResponse(
		ExternalProductGroup externalProductGroup,
		SellicResponse sellicResponse,
		String productName,
		BigDecimal regularPrice,
		BigDecimal currentPrice
	) {

		String externalProductGroupId = externalProductGroup.getExternalProductGroupId();
		if (externalProductGroupId == null || externalProductGroupId.isEmpty()) {
			externalProductGroupId = sellicResponse.productId();
		}

		return new SellicProductGroupRequestResponse(
			externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(),
			externalProductGroupId,
			SyncStatus.APPROVED,
			productName,
			regularPrice,
			currentPrice,
			false
		);
	}

}
