package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;

public interface GitHubWebhookHandler<T extends GitHubWebhookRequestDto, R> {

	R handle(T requestDto);
	boolean canHandle(Class<? extends GitHubWebhookRequestDto> dtoClass);
}
