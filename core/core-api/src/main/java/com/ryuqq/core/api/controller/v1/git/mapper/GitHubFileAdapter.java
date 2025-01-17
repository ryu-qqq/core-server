package com.ryuqq.core.api.controller.v1.git.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.PullRequestCommit;
import com.ryuqq.core.external.git.GitHubDataFetcher;

@Component
public class GitHubFileAdapter {

	private final GitHubDataFetcher gitHubDataFetcher;

	public GitHubFileAdapter(GitHubDataFetcher gitHubDataFetcher) {
		this.gitHubDataFetcher = gitHubDataFetcher;
	}

	public List<PullRequestCommit> fetchChangedFiles(String fullName, long pullNumber) {
		return gitHubDataFetcher.fetchCommitInfo(fullName, pullNumber).stream()
			.filter(g -> isJavaFile(g.filename()))
			.map(g -> new PullRequestCommit(
					g.gitCommitId(),
					g.filename(),
					g.status()
				)).toList();

	}

	private boolean isJavaFile(String filePath) {
		return filePath != null && filePath.endsWith(".java");
	}


}
