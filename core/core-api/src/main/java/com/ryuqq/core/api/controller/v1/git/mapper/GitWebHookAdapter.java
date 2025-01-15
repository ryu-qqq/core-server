package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.domain.git.GitMergeRequestEvent;

public interface GitWebHookAdapter <T> {
	GitMergeRequestEvent toMergeRequestEvent(T gitRequestDto);
}
