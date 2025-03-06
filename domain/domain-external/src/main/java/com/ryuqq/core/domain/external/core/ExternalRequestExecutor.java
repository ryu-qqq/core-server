package com.ryuqq.core.domain.external.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface ExternalRequestExecutor<T, R> {
	CompletableFuture<R> sendRequestAsync(T preparedRequestDto, ExecutorService executor); // 버츄얼 스레드 주입
}
