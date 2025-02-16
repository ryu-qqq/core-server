package com.ryuqq.core.api.controller.v1.external.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.external.request.BuyMaEventRequestDto;

@Component
public class BuyMaWebhookHandlerProvider {

	private final List<BuyMaWebhookHandler<? extends BuyMaEventRequestDto, ?>> handlers;

	public BuyMaWebhookHandlerProvider(List<BuyMaWebhookHandler<? extends BuyMaEventRequestDto, ?>> handlers) {
		this.handlers = handlers;
	}

	@SuppressWarnings("unchecked")
	public <T extends BuyMaEventRequestDto> BuyMaWebhookHandler<T, ?> getHandler(Class<T> dtoClass) {
		return (BuyMaWebhookHandler<T, ?>) handlers.stream()
			.filter(handler -> handler.canHandle(dtoClass))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("No BuyMa Webhook handler found for: " + dtoClass.getName()));
	}
}
