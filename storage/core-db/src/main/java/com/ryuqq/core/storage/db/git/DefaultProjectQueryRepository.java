package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.Project;
import com.ryuqq.core.domain.git.ProjectQueryRepository;
import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class DefaultProjectQueryRepository implements ProjectQueryRepository {

	private final ProjectDomainMapper projectDomainMapper;
	private final ProjectQueryDslRepository projectQueryDslRepository;

	public DefaultProjectQueryRepository(ProjectDomainMapper projectDomainMapper, ProjectQueryDslRepository projectQueryDslRepository) {
		this.projectDomainMapper = projectDomainMapper;
		this.projectQueryDslRepository = projectQueryDslRepository;
	}

	@Override
	public Project fetchByGitProjectIdAndGitType(long gitProjectId, GitType gitType) {
		return projectQueryDslRepository.fetchByGitProjectIdAndGitType(gitProjectId, gitType)
			.map(projectDomainMapper::toDomain)
			.orElseThrow(() -> new DataNotFoundException(String.format("Project not found %s, Git Type %s", gitProjectId, gitType)));
	}


}
