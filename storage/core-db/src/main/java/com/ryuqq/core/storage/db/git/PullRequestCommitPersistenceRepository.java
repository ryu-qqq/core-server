package com.ryuqq.core.storage.db.git;

public interface PullRequestCommitPersistenceRepository {
	void save(PullRequestCommitCommand pullRequestCommitCommand);
}
