package com.ryuqq.core.domain.git;

public interface PullRequestCommitPersistenceRepository {
	void save(PullRequestCommitCommand pullRequestCommitCommand);
}
