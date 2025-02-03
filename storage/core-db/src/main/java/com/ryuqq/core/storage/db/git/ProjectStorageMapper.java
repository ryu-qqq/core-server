package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.ProjectCommand;

@Component
public class ProjectStorageMapper {

	public ProjectEntity toEntity(ProjectCommand projectCommand) {
		if(projectCommand.id() != null){
			return new ProjectEntity(
				projectCommand.id(),
				projectCommand.gitProjectId(),
				projectCommand.gitType(),
				projectCommand.repositoryName(),
				projectCommand.repositoryUrl(),
				projectCommand.owner(),
				projectCommand.description()
			);
		}

		return new ProjectEntity(
			projectCommand.gitProjectId(),
			projectCommand.gitType(),
			projectCommand.repositoryName(),
			projectCommand.repositoryUrl(),
			projectCommand.owner(),
			projectCommand.description()
		);


	}
}
