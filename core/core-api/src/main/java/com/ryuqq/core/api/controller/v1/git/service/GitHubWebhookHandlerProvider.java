package com.ryuqq.core.api.controller.v1.git.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;

@Component
public class GitHubWebhookHandlerProvider {

	private final List<GitHubWebhookHandler<? extends GitHubWebhookRequestDto, ?>> handlers;

	public GitHubWebhookHandlerProvider(List<GitHubWebhookHandler<? extends GitHubWebhookRequestDto, ?>> handlers) {
		this.handlers = handlers;
	}

	@SuppressWarnings("unchecked")
	public <T extends GitHubWebhookRequestDto> GitHubWebhookHandler<T, ?> getHandler(Class<T> dtoClass) {
		return (GitHubWebhookHandler<T, ?>) handlers.stream()
			.filter(handler -> handler.canHandle(dtoClass))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("No GitHub Webhook handler found for: " + dtoClass.getName()));
	}


}
