package com.ryuqq.core.api.controller.v1.git.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.git.request.GitHubPushEventRequestDto;
import com.ryuqq.core.domain.git.Commit;

@Component
public class GitPushEventAdapter {

	private final GitCommitCreateAdapter gitCommitCreateAdapter;

	public GitPushEventAdapter(GitCommitCreateAdapter gitCommitCreateAdapter) {
		this.gitCommitCreateAdapter = gitCommitCreateAdapter;
	}

	public List<Commit> toCommits(GitHubPushEventRequestDto gitHubPushEventRequestDto){
		return gitCommitCreateAdapter.createCommitRequestDto(gitHubPushEventRequestDto.commits());
	}

}
