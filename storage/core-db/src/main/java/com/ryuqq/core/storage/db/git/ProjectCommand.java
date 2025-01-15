package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;

public record ProjectCommand (
	Long id,
	long gitProjectId,
	GitType gitType,
	String repositoryName,
	String repositoryUrl,
	String owner,
	String description
) {

	public ProjectEntity toEntity(){
		if(id != null){
			return new ProjectEntity(id, gitProjectId, gitType, repositoryName, repositoryUrl, owner, description);
		}
		return new ProjectEntity(gitProjectId, gitType, repositoryName, repositoryUrl, owner, description);
	}

}
