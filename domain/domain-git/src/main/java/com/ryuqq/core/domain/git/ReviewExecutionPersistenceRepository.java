package com.ryuqq.core.domain.git;

public interface ReviewExecutionPersistenceRepository {

	void save(ReviewExecutionCommand reviewExecutionCommand);

}
