package com.ryuqq.core.external.oco.core;

import org.springframework.stereotype.Service;

import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.oco.OcoClient;
import com.ryuqq.core.external.oco.OcoProductGroupInsertRequestContext;
import com.ryuqq.core.external.oco.OcoRequestResponseHandler;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestWrapperDto;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Service
public class OcoRequestExecutor extends AbstractOcoRequestExecutor<OcoProductGroupInsertRequestContext> {

	public OcoRequestExecutor(OcoClient ocoClient, FeignClientWrapper feignClientWrapper,
							  OcoRequestResponseHandler ocoRequestResponseHandler) {
		super(ocoClient, feignClientWrapper, ocoRequestResponseHandler);
	}

	@Override
	protected OcoProductInsertResponseDto handleRequest(OcoProductGroupInsertRequestContext requestDto) {
		OcoResponse<OcoProductInsertResponseDto> response = feignClientWrapper.executeFeignCall(
			() -> ocoClient.insertProduct(new OcoProductInsertRequestWrapperDto(requestDto.ocoProductInsertRequestDto()))
		);
		return ocoRequestResponseHandler.handleResponse(response);
	}
}
