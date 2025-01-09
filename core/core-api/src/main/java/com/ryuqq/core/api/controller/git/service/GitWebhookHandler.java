package com.ryuqq.core.api.controller.git.service;

import com.ryuqq.core.api.controller.git.mapper.GitWebHookAdapter;
import com.ryuqq.core.api.controller.git.request.GitPushEventRequestDto;
import com.ryuqq.core.api.controller.git.response.GitPushEventResponseDto;
import com.ryuqq.core.domain.GitEventRegistrar;

import org.springframework.stereotype.Component;

@Component
public class GitWebhookHandler {

	private final GitEventRegistrar gitEventRegistrar;
	private final GitWebHookAdapter gitWebHookAdapter;

	public GitWebhookHandler(GitEventRegistrar gitEventRegistrar, GitWebHookAdapter gitWebHookAdapter) {
		this.gitEventRegistrar = gitEventRegistrar;
		this.gitWebHookAdapter = gitWebHookAdapter;
	}

	public GitPushEventResponseDto handle(GitPushEventRequestDto gitPushEventRequestDto){
		long branchId = gitEventRegistrar.register(gitWebHookAdapter.toCommand(gitPushEventRequestDto));
		return new GitPushEventResponseDto(branchId);
	}


}
