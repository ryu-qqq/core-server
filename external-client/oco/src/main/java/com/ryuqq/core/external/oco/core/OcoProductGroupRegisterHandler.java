package com.ryuqq.core.external.oco.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.oco.OcoClient;
import com.ryuqq.core.external.oco.OcoProductGroupInsertRequestContext;
import com.ryuqq.core.external.oco.OcoRequestResponseHandler;
import com.ryuqq.core.external.oco.helper.OcoResponseFactory;
import com.ryuqq.core.external.oco.mapper.OcoProductMapper;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestWrapperDto;
import com.ryuqq.core.external.oco.response.OcoProductGroupRequestResponse;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Component
public class OcoProductGroupRegisterHandler implements UpdateTypeHandler {

	private final OcoProductMapper ocoProductMapper;
	private final OcoClient ocoClient;
	private final FeignClientWrapper feignClientWrapper;
	private final OcoRequestResponseHandler ocoRequestResponseHandler;

	public OcoProductGroupRegisterHandler(OcoProductMapper ocoProductMapper, OcoClient ocoClient,
										  FeignClientWrapper feignClientWrapper,
										  OcoRequestResponseHandler ocoRequestResponseHandler) {
		this.ocoProductMapper = ocoProductMapper;
		this.ocoClient = ocoClient;
		this.feignClientWrapper = feignClientWrapper;
		this.ocoRequestResponseHandler = ocoRequestResponseHandler;
	}

	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.OCO.equals(siteName) &&  ProductDomainEventType.PRODUCT_GROUP_REGISTER.equals(productDomainEventType);
	}

	@Override
	public OcoProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup) {
		OcoProductGroupInsertRequestContext requestDtoContext = ocoProductMapper.toInsetRequestDto(externalProductGroup);
		OcoResponse<OcoProductInsertResponseDto> ocoProductInsertResponseDtoOcoResponse = feignClientWrapper.executeFeignCall(
			() -> ocoClient.insertProduct(new OcoProductInsertRequestWrapperDto(requestDtoContext.ocoProductInsertRequestDto())));

		OcoProductInsertResponseDto ocoProductInsertResponseDto = ocoRequestResponseHandler.handleResponse(
			ocoProductInsertResponseDtoOcoResponse);

		return OcoResponseFactory.createProductInsertResponse(
			externalProductGroup, requestDtoContext, ocoProductInsertResponseDto
		);
	}
}
