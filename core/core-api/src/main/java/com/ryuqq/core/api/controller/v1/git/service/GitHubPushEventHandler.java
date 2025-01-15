package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.mapper.GitPushEventAdapter;
import com.ryuqq.core.api.controller.v1.git.request.GitHubPushEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitHubPushEventResponseDto;
import com.ryuqq.core.domain.git.Commit;
import com.ryuqq.core.domain.git.CommitAggregateRoot;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GitHubPushEventHandler implements GitHubWebhookHandler<GitHubPushEventRequestDto, GitHubPushEventResponseDto>{

	private final GitPushEventAdapter gitPushEventAdapter;
	private final CommitAggregateRoot commitAggregateRoot;

	public GitHubPushEventHandler(GitPushEventAdapter gitPushEventAdapter, CommitAggregateRoot commitAggregateRoot) {
		this.gitPushEventAdapter = gitPushEventAdapter;
		this.commitAggregateRoot = commitAggregateRoot;
	}

	@Override
	public GitHubPushEventResponseDto handle(GitHubPushEventRequestDto requestDto) {
		List<Commit> commits = gitPushEventAdapter.toCommits(requestDto);
		long branchId = commitAggregateRoot.processCommits(requestDto.getGitProjectId(), requestDto.branchName(), commits);
		return new GitHubPushEventResponseDto(branchId);
	}

	@Override
	public boolean canHandle(Class<? extends GitHubWebhookRequestDto> dtoClass) {
		return GitHubPushEventRequestDto.class.equals(dtoClass);
	}

}
