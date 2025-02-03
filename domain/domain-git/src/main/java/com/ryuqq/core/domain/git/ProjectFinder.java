package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.GitType;

@Component
public class ProjectFinder {

	private final ProjectQueryRepository projectQueryRepository;

	public ProjectFinder(ProjectQueryRepository projectQueryRepository) {
		this.projectQueryRepository = projectQueryRepository;
	}

	public Project fetchByGitProjectIdAndGitType(long gitProjectId, GitType gitType) {
		return projectQueryRepository.fetchByGitProjectIdAndGitType(gitProjectId, gitType);
	}

}
