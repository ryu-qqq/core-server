package com.ryuqq.core.storage.db.git;

public interface PullRequestPersistenceRepository {


	long save(PullRequestCommand pullRequestCommand);

}
