package com.ryuqq.core.api.controller.v1.git.service;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.git.mapper.GitBranchCreateAdapter;
import com.ryuqq.core.api.controller.v1.git.request.GitHubBranchCreateEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitHubBranchCreateEventResponseDto;
import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.BranchAggregateRoot;
import com.ryuqq.core.enums.GitType;

@Service
public class GitHubBranchCreateEventHandler implements GitHubWebhookHandler<GitHubBranchCreateEventRequestDto, GitHubBranchCreateEventResponseDto>{

	private final GitBranchCreateAdapter gitBranchCreateAdapter;
	private final BranchAggregateRoot branchAggregateRoot;

	public GitHubBranchCreateEventHandler(GitBranchCreateAdapter gitBranchCreateAdapter, BranchAggregateRoot branchAggregateRoot) {
		this.gitBranchCreateAdapter = gitBranchCreateAdapter;
		this.branchAggregateRoot = branchAggregateRoot;
	}

	@Override
	public GitHubBranchCreateEventResponseDto handle(GitHubBranchCreateEventRequestDto gitHubBranchCreateEventRequestDto) {
		Branch branch = gitBranchCreateAdapter.toCreateBranchRequestDto(gitHubBranchCreateEventRequestDto);
		long branchId = branchAggregateRoot.fetchOrRegisterBranch(gitHubBranchCreateEventRequestDto.getGitProjectId(), GitType.GIT_HUB, branch);
		return new GitHubBranchCreateEventResponseDto(branchId);
	}

	@Override
	public boolean canHandle(Class<? extends GitHubWebhookRequestDto> dtoClass) {
		return GitHubBranchCreateEventRequestDto.class.equals(dtoClass);
	}

}
