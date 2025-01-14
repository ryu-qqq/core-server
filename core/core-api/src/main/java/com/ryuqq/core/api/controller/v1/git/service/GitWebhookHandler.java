package com.ryuqq.core.api.controller.v1.git.service;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.git.mapper.GitWebHookAdapter;
import com.ryuqq.core.api.controller.v1.git.request.GitMergeEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidationResult;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidator;
import com.ryuqq.core.domain.git.GitMergeRequestManager;

@Component
public class GitWebhookHandler {

	private final GitMergeRequestManager gitMergeRequestManager;
	private final GitWebHookAdapter gitWebHookAdapter;
	private final LabelValidator labelValidator;

	public GitWebhookHandler(GitMergeRequestManager gitMergeRequestManager, GitWebHookAdapter gitWebHookAdapter,
							 LabelValidator labelValidator) {
		this.gitMergeRequestManager = gitMergeRequestManager;
		this.gitWebHookAdapter = gitWebHookAdapter;
		this.labelValidator = labelValidator;
	}

	public GitPushEventResponseDto handle(GitMergeEventRequestDto gitMergeEventRequestDto){
		LabelValidationResult result = labelValidator.validate(gitMergeEventRequestDto.labels());

		if (!result.isValid()) {
			throw new IllegalArgumentException("Invalid labels: " + result.getInvalidLabels());
		}

		long projectId = gitMergeRequestManager.register(gitWebHookAdapter.toMergeRequestEvent(gitMergeEventRequestDto));
		return new GitPushEventResponseDto(projectId);
	}


}
