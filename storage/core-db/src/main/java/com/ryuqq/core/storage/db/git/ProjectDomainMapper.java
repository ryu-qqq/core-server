package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.Project;
import com.ryuqq.core.storage.db.git.dto.ProjectDto;

@Component
public class ProjectDomainMapper {

	public Project toDomain(ProjectDto projectDto) {
		return Project.create(
			projectDto.getId(),
			projectDto.getGitProjectId(),
			projectDto.getGitType(),
			projectDto.getName(),
			projectDto.getRepositoryUrl(),
			projectDto.getOwner(),
			projectDto.getDescription()
		);
	}


}
