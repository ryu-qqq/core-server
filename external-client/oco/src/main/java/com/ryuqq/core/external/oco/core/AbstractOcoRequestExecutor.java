package com.ryuqq.core.external.oco.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import com.ryuqq.core.domain.external.core.ExternalRequestExecutor;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.oco.OcoClient;
import com.ryuqq.core.external.oco.OcoRequestResponseHandler;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;

public abstract class AbstractOcoRequestExecutor<T> implements ExternalRequestExecutor<T, OcoProductInsertResponseDto> {

	protected final OcoClient ocoClient;
	protected final FeignClientWrapper feignClientWrapper;
	protected final OcoRequestResponseHandler ocoRequestResponseHandler;

	protected AbstractOcoRequestExecutor(OcoClient ocoClient, FeignClientWrapper feignClientWrapper,
										 OcoRequestResponseHandler ocoRequestResponseHandler) {
		this.ocoClient = ocoClient;
		this.feignClientWrapper = feignClientWrapper;
		this.ocoRequestResponseHandler = ocoRequestResponseHandler;
	}

	protected abstract OcoProductInsertResponseDto handleRequest(T requestDto);

	@Override
	public CompletableFuture<OcoProductInsertResponseDto> sendRequestAsync(T requestDto, ExecutorService executor) {
		return CompletableFuture.supplyAsync(() -> handleRequest(requestDto), executor);
	}
}
