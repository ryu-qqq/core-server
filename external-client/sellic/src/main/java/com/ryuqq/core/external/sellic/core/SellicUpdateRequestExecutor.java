package com.ryuqq.core.external.sellic.core;

import org.springframework.stereotype.Service;

import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.sellic.SellicClient;
import com.ryuqq.core.external.sellic.SellicResponseHandler;
import com.ryuqq.core.external.sellic.request.SellicProductInsertRequestDto;
import com.ryuqq.core.external.sellic.response.SellicResponse;

@Service
public class SellicUpdateRequestExecutor extends AbstractSellicRequestExecutor<SellicProductInsertRequestDto>  {

	protected SellicUpdateRequestExecutor(SellicClient sellicClient, FeignClientWrapper feignClientWrapper,
                                          SellicResponseHandler sellicResponseHandler) {
		super(sellicClient, feignClientWrapper, sellicResponseHandler);
	}

	@Override
	protected SellicResponse handleRequest(SellicProductInsertRequestDto requestDto) {
		SellicResponse response = feignClientWrapper.executeFeignCall(
				() -> sellicClient.updateProduct(requestDto)
			);
		return sellicResponseHandler.handleResponse(response);
	}

}
