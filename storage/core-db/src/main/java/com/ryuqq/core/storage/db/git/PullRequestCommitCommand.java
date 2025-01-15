package com.ryuqq.core.storage.db.git;

public record PullRequestCommitCommand(
	long pullRequestId,
	long commitId
) {}
