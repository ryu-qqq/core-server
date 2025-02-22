package com.ryuqq.core.external.sellic.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import com.ryuqq.core.domain.external.core.ExternalRequestExecutor;
import com.ryuqq.core.external.FeignClientWrapper;
import com.ryuqq.core.external.sellic.SellicClient;
import com.ryuqq.core.external.sellic.SellicResponseHandler;
import com.ryuqq.core.external.sellic.response.SellicResponse;

public abstract class AbstractSellicRequestExecutor<T> implements ExternalRequestExecutor<T, SellicResponse> {

	protected final SellicClient sellicClient;
	protected final FeignClientWrapper feignClientWrapper;
	protected final SellicResponseHandler sellicResponseHandler;

	protected AbstractSellicRequestExecutor(SellicClient sellicClient, FeignClientWrapper feignClientWrapper,
											SellicResponseHandler sellicResponseHandler) {
		this.sellicClient = sellicClient;
		this.feignClientWrapper = feignClientWrapper;
		this.sellicResponseHandler = sellicResponseHandler;
	}

	protected abstract SellicResponse handleRequest(T requestDto);

	@Override
	public CompletableFuture<SellicResponse> sendRequestAsync(T requestDto, ExecutorService executor) {
		return CompletableFuture.supplyAsync(() -> handleRequest(requestDto), executor);
	}

}
