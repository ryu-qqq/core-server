package com.ryuqq.core.external.oco.core;

import org.springframework.stereotype.Service;

import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.oco.OcoClient;
import com.ryuqq.core.external.oco.OcoProductGroupUpdateRequestContext;
import com.ryuqq.core.external.oco.OcoRequestResponseHandler;
import com.ryuqq.core.external.oco.helper.OcoProductOptionUpdateFactory;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestWrapperDto;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;
import com.ryuqq.core.external.oco.response.OcoProductUpdateResponseDto;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Service
public class OcoUpdateRequestExecutor extends AbstractOcoRequestExecutor<OcoProductGroupUpdateRequestContext> {

	public OcoUpdateRequestExecutor(OcoClient ocoClient, FeignClientWrapper feignClientWrapper,
									OcoRequestResponseHandler ocoRequestResponseHandler) {
		super(ocoClient, feignClientWrapper, ocoRequestResponseHandler);
	}

	@Override
	protected OcoProductInsertResponseDto handleRequest(OcoProductGroupUpdateRequestContext requestDto) {
		if (!requestDto.deletedOcoOptionContexts().isEmpty()) {
			OcoResponse<OcoProductUpdateResponseDto> deleteResponse = feignClientWrapper.executeFeignCall(
				() -> ocoClient.deleteOption(
					OcoProductOptionUpdateFactory.createOcoOptionDeleteRequestDto(
						requestDto.ocoProductInsertRequestDto().pid(),
						requestDto.deletedOcoOptionContexts()
					)
				));
			ocoRequestResponseHandler.handleResponse(deleteResponse);
		}

		OcoResponse<OcoProductInsertResponseDto> response = feignClientWrapper.executeFeignCall(
			() -> ocoClient.insertProduct(new OcoProductInsertRequestWrapperDto(requestDto.ocoProductInsertRequestDto()))
		);
		return ocoRequestResponseHandler.handleResponse(response);
	}
}
