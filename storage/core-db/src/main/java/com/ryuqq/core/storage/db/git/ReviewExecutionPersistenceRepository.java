package com.ryuqq.core.storage.db.git;

public interface ReviewExecutionPersistenceRepository {

	void save(ReviewExecutionCommand reviewExecutionCommand);

}
