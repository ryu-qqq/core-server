package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.ProjectCommand;
import com.ryuqq.core.domain.git.ProjectPersistenceRepository;

@Repository
public class ProjectJpaPersistenceRepository implements ProjectPersistenceRepository {

	private final ProjectStorageMapper projectStorageMapper;
	private final ProjectJpaRepository projectJpaRepository;

	public ProjectJpaPersistenceRepository(ProjectStorageMapper projectStorageMapper, ProjectJpaRepository projectJpaRepository) {
		this.projectStorageMapper = projectStorageMapper;
		this.projectJpaRepository = projectJpaRepository;
	}

	@Override
	public long save(ProjectCommand projectCommand) {
		return projectJpaRepository.save(projectStorageMapper.toEntity(projectCommand)).getId();
	}

}
