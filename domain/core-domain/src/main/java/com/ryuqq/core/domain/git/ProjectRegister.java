package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.ProjectPersistenceRepository;

import org.springframework.stereotype.Component;

@Component
public class ProjectRegister {

	private final ProjectPersistenceRepository projectPersistenceRepository;

	public ProjectRegister(ProjectPersistenceRepository projectPersistenceRepository) {
		this.projectPersistenceRepository = projectPersistenceRepository;
	}

	public long register(Project project) {
		return projectPersistenceRepository.save(project.toCommand());
	}
}
