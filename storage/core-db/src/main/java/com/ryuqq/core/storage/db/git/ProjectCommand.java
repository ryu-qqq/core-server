package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;

public record ProjectCommand (
	Long id,
	Long gitlabProjectId,
	GitType gitType,
	String name,
	String repositoryUrl,
	String owner,
	String description
) {
	public ProjectEntity toEntity(){
		if(id != null){
			return new ProjectEntity(id, gitlabProjectId, gitType, name, repositoryUrl, owner, description);
		}
		return new ProjectEntity(gitlabProjectId, gitType, name, repositoryUrl, owner, description);
	}

}
