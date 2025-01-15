package com.ryuqq.core.storage.db.git;

public interface PullRequestChangedFilePersistenceRepository {

	void save(PullRequestChangedFileCommand pullRequestChangedFileCommand);
}
