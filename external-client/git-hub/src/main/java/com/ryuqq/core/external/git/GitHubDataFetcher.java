package com.ryuqq.core.external.git;

import com.ryuqq.core.external.git.response.GitHubCommitResponse;
import com.ryuqq.core.external.git.response.GitHubPullRequestFileResponse;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GitHubDataFetcher {

	private final GitHubFeignClient gitHubFeignClient;

	public GitHubDataFetcher(GitHubFeignClient gitHubFeignClient) {
		this.gitHubFeignClient = gitHubFeignClient;
	}

	public GitHubCommitResponse fetchCommitInfo(String commitUrl) {
		return gitHubFeignClient.getCommitInfo(commitUrl);
	}


	public List<GitHubPullRequestFileResponse> fetchChangedFiles(String owner, String repo, int pullNumber) {
		return gitHubFeignClient.getPullRequestFiles(owner, repo, pullNumber);
	}

}
