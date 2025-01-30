package com.ryuqq.core.external.buyma.helper;

import java.math.BigDecimal;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponse;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponseDto;

public class BuyMaResponseFactory {

	public static BuyMaProductInsertResponse createProductResponse(
		ExternalProductGroup externalProductGroup,
		BuyMaProductInsertResponseDto buyMaProductInsertResponseDto,
		String productName,
		BigDecimal regularPrice,
		BigDecimal currentPrice
	) {
		return new BuyMaProductInsertResponse(
			externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(),
			buyMaProductInsertResponseDto.requestUid(),
			SyncStatus.APPROVED,
			productName,
			regularPrice,
			currentPrice,
			false
		);
	}

}
