package com.ryuqq.core.storage.db.git;

public interface ProjectPersistenceRepository {

	long save(ProjectCommand projectCommand);
}
