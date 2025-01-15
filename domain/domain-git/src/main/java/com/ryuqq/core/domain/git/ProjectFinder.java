package com.ryuqq.core.domain.git;

import com.ryuqq.core.domain.exception.DataNotFoundException;
import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.storage.db.git.ProjectQueryRepository;

import org.springframework.stereotype.Component;

@Component
public class ProjectFinder {

	private final ProjectMapper projectMapper;
	private final ProjectQueryRepository projectQueryRepository;

	public ProjectFinder(ProjectMapper projectMapper, ProjectQueryRepository projectQueryRepository) {
		this.projectMapper = projectMapper;
		this.projectQueryRepository = projectQueryRepository;
	}

	public Project fetchByGitProjectIdAndGitType(long gitProjectId, GitType gitType) {
		return projectQueryRepository.fetchByGitProjectIdAndGitType(gitProjectId, gitType)
			.map(projectMapper::toDomain)
			.orElseThrow(() -> new DataNotFoundException(String.format("Project not found %s, Git Type %s", gitProjectId, gitType)));
	}

}
