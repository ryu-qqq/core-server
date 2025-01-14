package com.ryuqq.core.external.git.response;

public record GitHubPullRequestFileResponse(
	String filename,
	String status,
	int additions,
	int deletions,
	int changes
) {
}
