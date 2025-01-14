package com.ryuqq.core.domain.git;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.ProjectQueryRepository;

@Component
public class ProjectFinder {

	private final ProjectMapper projectMapper;
	private final ProjectQueryRepository projectQueryRepository;

	public ProjectFinder(ProjectMapper projectMapper, ProjectQueryRepository projectQueryRepository) {
		this.projectMapper = projectMapper;
		this.projectQueryRepository = projectQueryRepository;
	}

	public Optional<Project> findByGitProjectId(long gitProjectId) {
		return projectQueryRepository.fetchByGitProjectId(gitProjectId)
			.map(projectMapper::toDomain);

	}



}
