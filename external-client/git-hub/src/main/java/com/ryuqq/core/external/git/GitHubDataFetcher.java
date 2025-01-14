package com.ryuqq.core.external.git;

import com.ryuqq.core.external.git.response.GitHubCommitResponse;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GitHubDataFetcher {

	private final GitHubFeignClient gitHubFeignClient;

	public GitHubDataFetcher(GitHubFeignClient gitHubFeignClient) {
		this.gitHubFeignClient = gitHubFeignClient;
	}

	public List<GitHubCommitResponse> fetchCommitInfo(String fullName, int pullNumber) {
		return gitHubFeignClient.getCommitInfos(fullName, pullNumber);
	}

}
