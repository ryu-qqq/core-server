package com.ryuqq.core.api.controller.v1.git.service;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.git.mapper.GitWebHookAdapter;
import com.ryuqq.core.api.controller.v1.git.request.GitLabMergeEventWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidationResult;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidator;
import com.ryuqq.core.domain.git.GitMergeRequestManager;

@Component
public class GitLabWebhookHandler implements GitWebhookHandler<GitLabMergeEventWebhookRequestDto> {

	private final GitMergeRequestManager gitMergeRequestManager;
	private final GitWebHookAdapter<GitLabMergeEventWebhookRequestDto> gitWebHookAdapter;
	private final LabelValidator labelValidator;

	public GitLabWebhookHandler(GitMergeRequestManager gitMergeRequestManager,
								GitWebHookAdapter<GitLabMergeEventWebhookRequestDto> gitWebHookAdapter,
								LabelValidator labelValidator) {
		this.gitMergeRequestManager = gitMergeRequestManager;
		this.gitWebHookAdapter = gitWebHookAdapter;
		this.labelValidator = labelValidator;
	}

	@Override
	public GitPushEventResponseDto handle(GitLabMergeEventWebhookRequestDto gitLabMergeEventRequestDto) {
		LabelValidationResult result = labelValidator.validate(gitLabMergeEventRequestDto.getLabelNames());

		if (!result.isValid()) {
			throw new IllegalArgumentException("Invalid labels: " + result.getInvalidLabels());
		}

		long projectId = gitMergeRequestManager.register(
			gitWebHookAdapter.toMergeRequestEvent(gitLabMergeEventRequestDto)
		);

		return new GitPushEventResponseDto(projectId);
	}

	@Override
	public boolean canHandle(Class<? extends GitWebhookRequestDto> dtoClass) {
		return dtoClass.equals(GitLabMergeEventWebhookRequestDto.class);
	}

}
