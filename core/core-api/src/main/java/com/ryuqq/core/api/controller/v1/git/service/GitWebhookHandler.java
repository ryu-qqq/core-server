package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.request.GitWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitPushEventResponseDto;

public interface GitWebhookHandler<T extends GitWebhookRequestDto> {
	GitPushEventResponseDto handle(T gitWebhookRequestDto);
	boolean canHandle(Class<? extends GitWebhookRequestDto> dtoClass);

}
