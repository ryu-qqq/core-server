package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.mapper.GitWebHookAdapter;
import com.ryuqq.core.api.controller.v1.git.request.GitHubMergeEventWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidationResult;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidator;
import com.ryuqq.core.domain.git.GitMergeRequestManager;

import org.springframework.stereotype.Component;

@Component
public class GitHubWebhookHandler implements GitWebhookHandler<GitHubMergeEventWebhookRequestDto> {

	private final GitMergeRequestManager gitMergeRequestManager;
	private final GitWebHookAdapter<GitHubMergeEventWebhookRequestDto> gitWebHookAdapter;
	private final LabelValidator labelValidator;

	public GitHubWebhookHandler(GitMergeRequestManager gitMergeRequestManager,
                                GitWebHookAdapter<GitHubMergeEventWebhookRequestDto> gitWebHookAdapter,
                                LabelValidator labelValidator) {
		this.gitMergeRequestManager = gitMergeRequestManager;
		this.gitWebHookAdapter = gitWebHookAdapter;
		this.labelValidator = labelValidator;
	}

	@Override
	public GitPushEventResponseDto handle(GitHubMergeEventWebhookRequestDto gitHubMergeEventWebhookRequestDto) {
		LabelValidationResult result = labelValidator.validate(gitHubMergeEventWebhookRequestDto.getLabelNames());

		if (!result.isValid()) {
			throw new IllegalArgumentException("Invalid labels: " + result.getInvalidLabels());
		}

		long projectId = gitMergeRequestManager.register(
			gitWebHookAdapter.toMergeRequestEvent(gitHubMergeEventWebhookRequestDto)
		);

		return new GitPushEventResponseDto(projectId);
	}

	@Override
	public boolean canHandle(Class<? extends GitWebhookRequestDto> dtoClass) {
		return dtoClass.equals(GitHubMergeEventWebhookRequestDto.class);
	}

}
