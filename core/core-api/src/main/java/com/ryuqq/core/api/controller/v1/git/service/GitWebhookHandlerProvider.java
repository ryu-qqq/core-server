package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.request.GitWebhookRequestDto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GitWebhookHandlerProvider {

	private final List<GitWebhookHandler<? extends GitWebhookRequestDto>> handlers;

	public GitWebhookHandlerProvider(List<GitWebhookHandler<? extends GitWebhookRequestDto>> handlers) {
		this.handlers = handlers;
	}

	@SuppressWarnings("unchecked")
	public <T extends GitWebhookRequestDto> GitWebhookHandler<T> getHandler(Class<T> dtoClass) {
		return (GitWebhookHandler<T>) handlers.stream()
			.filter(handler -> handler.canHandle(dtoClass))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("No Git WebHook handler found for: " + dtoClass.getName()));
	}



}
