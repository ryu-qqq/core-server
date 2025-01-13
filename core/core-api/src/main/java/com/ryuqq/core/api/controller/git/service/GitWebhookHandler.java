package com.ryuqq.core.api.controller.git.service;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.git.mapper.GitWebHookAdapter;
import com.ryuqq.core.api.controller.git.request.GitPushEventRequestDto;
import com.ryuqq.core.api.controller.git.response.GitPushEventResponseDto;
import com.ryuqq.core.domain.GitEventRegistrar;

@Component
public class GitWebhookHandler {

	private final GitEventRegistrar gitEventRegistrar;
	private final GitWebHookAdapter gitWebHookAdapter;

	public GitWebhookHandler(GitEventRegistrar gitEventRegistrar, GitWebHookAdapter gitWebHookAdapter) {
		this.gitEventRegistrar = gitEventRegistrar;
		this.gitWebHookAdapter = gitWebHookAdapter;
	}

	public GitPushEventResponseDto handle(GitPushEventRequestDto gitPushEventRequestDto){
		long branchId = gitEventRegistrar.register(gitWebHookAdapter.toDomain(gitPushEventRequestDto));
		return new GitPushEventResponseDto(branchId);
	}


}
