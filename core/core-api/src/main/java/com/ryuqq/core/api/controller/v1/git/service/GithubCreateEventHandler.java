package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.mapper.GitBranchCreateAdapter;
import com.ryuqq.core.api.controller.v1.git.request.CreateBranchRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubCreateEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitHubCreateEventResponseDto;
import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.BranchFactory;
import com.ryuqq.core.domain.git.BranchManager;
import com.ryuqq.core.domain.git.Project;
import com.ryuqq.core.domain.git.ProjectFinder;

import org.springframework.stereotype.Component;

@Component
public class GithubCreateEventHandler implements GitHubWebhookHandler<GitHubCreateEventRequestDto, GitHubCreateEventResponseDto>{

	private final GitBranchCreateAdapter gitBranchCreateAdapter;
	private final ProjectFinder projectFinder;
	private final BranchManager branchManager;

	public GithubCreateEventHandler(GitBranchCreateAdapter gitBranchCreateAdapter, ProjectFinder projectFinder, BranchManager branchManager) {
		this.gitBranchCreateAdapter = gitBranchCreateAdapter;
		this.projectFinder = projectFinder;
		this.branchManager = branchManager;
	}

	@Override
	public GitHubCreateEventResponseDto handle(GitHubCreateEventRequestDto gitHubCreateEventRequestDto) {
		CreateBranchRequestDto requestDto = gitBranchCreateAdapter.toCreateBranchRequestDto(gitHubCreateEventRequestDto);
		Project project = projectFinder.fetchByGitProjectIdAndGitType(requestDto.gitProjectId(), requestDto.gitType());
		Branch branch = BranchFactory.create(project.getId(), requestDto.branchName(), requestDto.baseBranchName());
		long branchId = branchManager.fetchOrRegisterBranch(branch);
		return new GitHubCreateEventResponseDto(branchId);
	}

	@Override
	public boolean canHandle(Class<? extends GitHubWebhookRequestDto> dtoClass) {
		return GitHubCreateEventRequestDto.class.equals(dtoClass);
	}

}
