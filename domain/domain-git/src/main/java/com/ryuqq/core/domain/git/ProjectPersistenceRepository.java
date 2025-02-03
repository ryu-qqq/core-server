package com.ryuqq.core.domain.git;

public interface ProjectPersistenceRepository {

	long save(ProjectCommand projectCommand);
}
