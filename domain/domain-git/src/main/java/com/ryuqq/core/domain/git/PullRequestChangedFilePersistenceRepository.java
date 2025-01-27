package com.ryuqq.core.domain.git;

public interface PullRequestChangedFilePersistenceRepository {

	void save(PullRequestChangedFileCommand pullRequestChangedFileCommand);
}
