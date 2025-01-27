package com.ryuqq.core.domain.git;

public record PullRequestCommitCommand(
	long pullRequestId,
	long commitId
) {

}
