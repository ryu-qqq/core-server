package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.dto.ProjectDto;

@Component
public class ProjectMapper {

	public Project toDomain(ProjectDto projectDto) {
		return new Project(
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
