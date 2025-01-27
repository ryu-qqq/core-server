package com.ryuqq.core.external.oco.helper;

import java.math.BigDecimal;
import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.OcoProductIdMapping;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;

public class OcoProductResponseHelper {

	public static List<OcoProductIdMapping> createProductIdMappings(
		List<OcoOptionContext> optionContexts,
		OcoProductInsertResponseDto responseDto
	) {
		return OcoProductIdMappingHelper.toOcoProductIdMappingList(optionContexts, responseDto);
	}

	public static List<OcoProductIdMapping> createDeletedProductIdMappings(List<OcoOptionContext> deletedContexts) {
		return OcoProductIdMappingHelper.toOcoProductIdMappingListForDelete(deletedContexts);
	}

	public static OcoProductGroupRequestResponse createResponse(
		ExternalProductGroup externalProductGroup,
		String externalProductGroupId,
		String productName,
		BigDecimal regularPrice,
		BigDecimal currentPrice,
		List<OcoProductIdMapping> productIdMappings
	) {
		return new OcoProductGroupRequestResponse(
			externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(),
			externalProductGroupId,
			SyncStatus.APPROVED,
			productName,
			regularPrice,
			currentPrice,
			false,
			productIdMappings
		);
	}

}
