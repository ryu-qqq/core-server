package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.GitWebhookRequestDto;
import com.ryuqq.core.domain.git.GitMergeRequestEvent;

public interface GitWebHookAdapter <T extends GitWebhookRequestDto> {
	GitMergeRequestEvent toMergeRequestEvent(T gitRequestDto);
}
