package com.ryuqq.core.external.oco.helper;

import java.math.BigDecimal;
import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.OcoProductGroupInsertRequestContext;
import com.ryuqq.core.external.oco.OcoProductGroupUpdateRequestContext;
import com.ryuqq.core.external.oco.OcoProductIdMapping;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductPriceUpdateRequestDto;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;

public class OcoResponseFactory {

	public static OcoProductGroupRequestResponse createProductInsertResponse(
		ExternalProductGroup externalProductGroup,
		OcoProductGroupInsertRequestContext requestDtoContext,
		OcoProductInsertResponseDto responseDto
	) {
		OcoProductInsertRequestDto product = requestDtoContext.ocoProductInsertRequestDto();
		List<OcoOptionContext> optionContexts = requestDtoContext.ocoOptionContexts();

		List<OcoProductIdMapping> productIdMappings = OcoProductResponseHelper.createProductIdMappings(optionContexts, responseDto);

		return OcoProductResponseHelper.createResponse(
			externalProductGroup,
			String.valueOf(responseDto.pid()),
			product.name(),
			BigDecimal.valueOf(product.originPrice()),
			BigDecimal.valueOf(product.price()),
			productIdMappings
		);
	}


	public static OcoProductGroupRequestResponse createProductUpdateResponse(
		ExternalProductGroup externalProductGroup,
		OcoProductGroupUpdateRequestContext updateRequestDto,
		OcoProductInsertResponseDto responseDto
	) {
		List<OcoOptionContext> optionContexts = updateRequestDto.ocoOptionContexts();
		List<OcoOptionContext> deletedContexts = updateRequestDto.deletedOcoOptionContexts();

		List<OcoProductIdMapping> productIdMappings = OcoProductResponseHelper.createProductIdMappings(optionContexts, responseDto);
		productIdMappings.addAll(OcoProductResponseHelper.createDeletedProductIdMappings(deletedContexts));

		return OcoProductResponseHelper.createResponse(
			externalProductGroup,
			String.valueOf(responseDto.pid()),
			updateRequestDto.ocoProductInsertRequestDto().name(),
			BigDecimal.valueOf(updateRequestDto.ocoProductInsertRequestDto().originPrice()),
			BigDecimal.valueOf(updateRequestDto.ocoProductInsertRequestDto().price()),
			productIdMappings
		);
	}

	public static OcoProductGroupRequestResponse createPriceUpdateResponse(
		ExternalProductGroup externalProductGroup,
		OcoProductPriceUpdateRequestDto updateRequestDto
	) {

		return OcoProductResponseHelper.createResponse(
			externalProductGroup,
			String.valueOf(externalProductGroup.getProductGroupId()),
			externalProductGroup.getProductName(),
			BigDecimal.valueOf(updateRequestDto.product().regularPrice()),
			BigDecimal.valueOf(updateRequestDto.product().currentPrice()),
			List.of()
		);

	}


	public static OcoProductGroupRequestResponse createStockUpdateResponse(
		ExternalProductGroup externalProductGroup,
		List<OcoOptionContext> ocoOptionContexts,
		List<OcoOptionContext> deletedContexts,
		OcoProductInsertResponseDto responseDto
	) {

		List<OcoProductIdMapping> productIdMappings = OcoProductResponseHelper.createProductIdMappings(ocoOptionContexts, responseDto);
		productIdMappings.addAll(OcoProductResponseHelper.createDeletedProductIdMappings(deletedContexts));

		return OcoProductResponseHelper.createResponse(
			externalProductGroup,
			String.valueOf(responseDto.pid()),
			externalProductGroup.getProductName(),
			BigDecimal.ZERO,
			BigDecimal.ZERO,
			productIdMappings
		);
	}

}
