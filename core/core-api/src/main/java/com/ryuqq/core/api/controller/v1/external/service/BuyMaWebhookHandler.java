package com.ryuqq.core.api.controller.v1.external.service;

import com.ryuqq.core.api.controller.v1.external.request.BuyMaEventRequestDto;

public interface BuyMaWebhookHandler<T extends BuyMaEventRequestDto, R>  {

	R handle(T requestDto);
	boolean canHandle(Class<? extends BuyMaEventRequestDto> dtoClass);
}
