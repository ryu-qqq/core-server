package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.GitHubPullRequestEventDto;
import com.ryuqq.core.domain.git.PullRequest;
import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.ReviewStatus;

import org.springframework.stereotype.Component;

@Component
public class GitPullRequestCreateAdapter {

	private final GitHubFileAdapter gitHubFileAdapter;

	public GitPullRequestCreateAdapter(GitHubFileAdapter gitHubFileAdapter) {
		this.gitHubFileAdapter = gitHubFileAdapter;
	}

	public PullRequest toPullRequest(GitHubPullRequestEventDto gitHubPullRequestEventDto) {
		return new PullRequest(
			gitHubPullRequestEventDto.getGitProjectId(),
			GitType.GIT_HUB,
			gitHubPullRequestEventDto.gitPullId(),
			gitHubPullRequestEventDto.getSourceBranch(),
			gitHubPullRequestEventDto.getTargetBranch(),
			gitHubPullRequestEventDto.getTitle(),
			gitHubPullRequestEventDto.getDescription(),
			gitHubPullRequestEventDto.getMergeStatus(),
			ReviewStatus.PENDING,
			gitHubFileAdapter.fetchChangedFiles(gitHubPullRequestEventDto.getFullName(), gitHubPullRequestEventDto.gitPullId())
		);
	}
}
