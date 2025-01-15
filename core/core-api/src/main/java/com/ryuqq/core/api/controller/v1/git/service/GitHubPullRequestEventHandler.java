package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.mapper.GitPullRequestCreateAdapter;
import com.ryuqq.core.api.controller.v1.git.request.GitHubPullRequestEventDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitHubPullRequestEventResponseDto;
import com.ryuqq.core.domain.git.PullRequest;
import com.ryuqq.core.domain.git.PullRequestAggregateRoot;

import org.springframework.stereotype.Component;

@Component
public class GitHubPullRequestEventHandler implements GitHubWebhookHandler<GitHubPullRequestEventDto, GitHubPullRequestEventResponseDto>{

	private final GitPullRequestCreateAdapter gitPullRequestCreateAdapter;
	private final PullRequestAggregateRoot pullRequestAggregateRoot;

	public GitHubPullRequestEventHandler(GitPullRequestCreateAdapter gitPullRequestCreateAdapter,
										 PullRequestAggregateRoot pullRequestAggregateRoot) {
		this.gitPullRequestCreateAdapter = gitPullRequestCreateAdapter;
		this.pullRequestAggregateRoot = pullRequestAggregateRoot;
	}

	@Override
	public GitHubPullRequestEventResponseDto handle(GitHubPullRequestEventDto requestDto) {
		PullRequest pullRequest = gitPullRequestCreateAdapter.toPullRequest(requestDto);

		if(!pullRequest.isOpened()) return new GitHubPullRequestEventResponseDto(0L);

		long pullRequestId = pullRequestAggregateRoot.processPullRequest(pullRequest);
		return new GitHubPullRequestEventResponseDto(pullRequestId);
	}

	@Override
	public boolean canHandle(Class<? extends GitHubWebhookRequestDto> dtoClass) {
		return GitHubPullRequestEventDto.class.equals(dtoClass);
	}
}
