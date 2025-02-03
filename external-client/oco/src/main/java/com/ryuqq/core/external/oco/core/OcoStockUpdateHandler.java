package com.ryuqq.core.external.oco.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.oco.OcoClient;
import com.ryuqq.core.external.oco.OcoOptionUpdateRequestContext;
import com.ryuqq.core.external.oco.OcoRequestResponseHandler;
import com.ryuqq.core.external.oco.helper.OcoProductOptionUpdateFactory;
import com.ryuqq.core.external.oco.helper.OcoResponseFactory;
import com.ryuqq.core.external.oco.mapper.OcoStockMapper;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;
import com.ryuqq.core.external.oco.response.OcoProductUpdateResponseDto;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Component
public class OcoStockUpdateHandler implements UpdateTypeHandler {

	private final OcoStockMapper ocoStockMapper;
	private final OcoClient ocoClient;
	private final FeignClientWrapper feignClientWrapper;
	private final OcoRequestResponseHandler ocoRequestResponseHandler;

	public OcoStockUpdateHandler(OcoStockMapper ocoStockMapper, OcoClient ocoClient, FeignClientWrapper feignClientWrapper,
								 OcoRequestResponseHandler ocoRequestResponseHandler) {
		this.ocoStockMapper = ocoStockMapper;
		this.ocoClient = ocoClient;
		this.feignClientWrapper = feignClientWrapper;
		this.ocoRequestResponseHandler = ocoRequestResponseHandler;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.OCO.equals(siteName) && ProductDomainEventType.STOCK.equals(productDomainEventType);
	}

	@Override
	public OcoProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup) {
		OcoOptionUpdateRequestContext updateContext = ocoStockMapper.toOcoOptionUpdateRequestContext(
			externalProductGroup.getProductGroupId(), externalProductGroup.getExternalProductGroupId());

		if(!updateContext.isDeleteRequest()){
			OcoResponse<OcoProductUpdateResponseDto> deleteResponse = feignClientWrapper.executeFeignCall(
				() -> ocoClient.deleteOption(
					OcoProductOptionUpdateFactory.createOcoOptionDeleteRequestDto(
						externalProductGroup.getExternalProductGroupId(),
						updateContext.deletedOcoOptionContexts()
					)
				));

			ocoRequestResponseHandler.handleResponse(deleteResponse);
		}

		OcoResponse<OcoProductInsertResponseDto> updateResponse = feignClientWrapper.executeFeignCall(
			() -> ocoClient.updateOption(
				OcoProductOptionUpdateFactory.createOcoOptionUpdateRequestDto(
					externalProductGroup.getExternalProductGroupId(),
					updateContext.updatedOcoOptionContexts()
				)
			));

		OcoProductInsertResponseDto responseDto = ocoRequestResponseHandler.handleResponse(updateResponse);


		return OcoResponseFactory.createStockUpdateResponse(
			externalProductGroup,
			updateContext.updatedOcoOptionContexts(),
			updateContext.deletedOcoOptionContexts(),
			responseDto
		);

	}
}
