package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.dto.ProjectDto;

import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

	public Project toDomain(ProjectDto projectDto) {
		return new Project(
			projectDto.getId(),
			projectDto.getGitlabProjectId(),
			projectDto.getName(),
			projectDto.getRepositoryUrl(),
			projectDto.getOwner(),
			projectDto.getDescription()
			);
	}
}
