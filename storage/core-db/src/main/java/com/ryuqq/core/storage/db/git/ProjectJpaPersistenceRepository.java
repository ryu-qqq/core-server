package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectJpaPersistenceRepository implements ProjectPersistenceRepository {

	private final ProjectJpaRepository projectJpaRepository;

	public ProjectJpaPersistenceRepository(ProjectJpaRepository projectJpaRepository) {
		this.projectJpaRepository = projectJpaRepository;
	}

	@Override
	public long save(ProjectCommand projectCommand) {
		return projectJpaRepository.save(projectCommand.toEntity()).getId();
	}

}
