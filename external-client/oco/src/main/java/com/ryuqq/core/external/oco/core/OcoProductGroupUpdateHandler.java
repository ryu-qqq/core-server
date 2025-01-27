package com.ryuqq.core.external.oco.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.oco.OcoClient;
import com.ryuqq.core.external.oco.OcoProductGroupUpdateRequestContext;
import com.ryuqq.core.external.oco.OcoRequestResponseHandler;
import com.ryuqq.core.external.oco.helper.OcoProductOptionUpdateFactory;
import com.ryuqq.core.external.oco.helper.OcoResponseFactory;
import com.ryuqq.core.external.oco.mapper.OcoProductMapper;
import com.ryuqq.core.external.oco.request.OcoProductUpdateRequestWrapperDto;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;
import com.ryuqq.core.external.oco.response.OcoProductUpdateResponseDto;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Component
public class OcoProductGroupUpdateHandler implements UpdateTypeHandler {

	private final OcoProductMapper ocoProductMapper;
	private final OcoClient ocoClient;
	private final FeignClientWrapper feignClientWrapper;
	private final OcoRequestResponseHandler ocoRequestResponseHandler;

	public OcoProductGroupUpdateHandler(OcoProductMapper ocoProductMapper, OcoClient ocoClient,
										FeignClientWrapper feignClientWrapper,
										OcoRequestResponseHandler ocoRequestResponseHandler) {
		this.ocoProductMapper = ocoProductMapper;
		this.ocoClient = ocoClient;
		this.feignClientWrapper = feignClientWrapper;
		this.ocoRequestResponseHandler = ocoRequestResponseHandler;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.OCO.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP.equals(productDomainEventType);
	}

	@Override
	public OcoProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup) {
		 OcoProductGroupUpdateRequestContext updateRequestDto= ocoProductMapper.toUpdateRequestDto(
			externalProductGroup);

		 if(!updateRequestDto.deletedOcoOptionContexts().isEmpty()){

			 OcoResponse<OcoProductUpdateResponseDto> ocoProductUpdateResponseDtoOcoResponse = feignClientWrapper.executeFeignCall(
				 () -> ocoClient.deleteOption(
					 OcoProductOptionUpdateFactory.createOcoOptionDeleteRequestDto(
						 externalProductGroup.getExternalProductGroupId(),
						 updateRequestDto.deletedOcoOptionContexts()
					 )
				 ));

			 ocoRequestResponseHandler.handleResponse(ocoProductUpdateResponseDtoOcoResponse);
		 }

		OcoResponse<OcoProductInsertResponseDto> ocoProductInsertResponseDtoOcoResponse = feignClientWrapper.executeFeignCall(
			() ->
				ocoClient.updateProduct(new OcoProductUpdateRequestWrapperDto(
					Integer.parseInt(externalProductGroup.getExternalProductGroupId()), updateRequestDto.ocoProductInsertRequestDto())));

		OcoProductInsertResponseDto ocoProductInsertResponseDto = ocoRequestResponseHandler.handleResponse(
			ocoProductInsertResponseDtoOcoResponse);

		return OcoResponseFactory.createProductUpdateResponse(
			externalProductGroup, updateRequestDto, ocoProductInsertResponseDto
		);
	}

}
