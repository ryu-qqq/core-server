package com.ryuqq.core.storage.db.git;

public interface CommitPersistenceRepository {
	long save(CommitCommand commitCommand);
}
