package com.ryuqq.core.external.git;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.external.git.response.GitHubCommitResponse;

@Component
public class GitHubDataFetcher {

	private final GitHubFeignClient gitHubFeignClient;

	public GitHubDataFetcher(GitHubFeignClient gitHubFeignClient) {
		this.gitHubFeignClient = gitHubFeignClient;
	}

	public List<GitHubCommitResponse> fetchCommitInfo(String fullName, long pullNumber) {
		return gitHubFeignClient.getCommitInfos(fullName, pullNumber);
	}

}
