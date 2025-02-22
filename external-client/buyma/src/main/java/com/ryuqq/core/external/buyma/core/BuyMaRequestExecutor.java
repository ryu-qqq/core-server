package com.ryuqq.core.external.buyma.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.core.ExternalRequestExecutor;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.buyma.BuyMaClient;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestWrapperDto;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponseDto;

@Service
public class BuyMaRequestExecutor implements ExternalRequestExecutor<BuyMaProductInsertRequestDto, BuyMaProductInsertResponseDto>  {

	private final BuyMaClient buyMaClient;
	private final FeignClientWrapper feignClientWrapper;

	public BuyMaRequestExecutor(BuyMaClient buyMaClient, FeignClientWrapper feignClientWrapper) {
		this.buyMaClient = buyMaClient;
		this.feignClientWrapper = feignClientWrapper;
	}


	@Override
	public CompletableFuture<BuyMaProductInsertResponseDto> sendRequestAsync(BuyMaProductInsertRequestDto requestDto, ExecutorService executor) {
		return CompletableFuture.supplyAsync(() -> feignClientWrapper.executeFeignCall(
			() -> buyMaClient.insertProduct(new BuyMaProductInsertRequestWrapperDto(requestDto))
		), executor);
	}
}
