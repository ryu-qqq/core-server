package com.ryuqq.core.domain.git;

public interface CommitPersistenceRepository {
	long save(CommitCommand commitCommand);
}
