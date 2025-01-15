package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;

public record ProjectCommand (
	Long id,
<<<<<<< HEAD
	long gitProjectId,
	GitType gitType,
	String repositoryName,
=======
	Long gitlabProjectId,
	GitType gitType,
	String name,
>>>>>>> main
	String repositoryUrl,
	String owner,
	String description
) {

	public ProjectEntity toEntity(){
		if(id != null){
<<<<<<< HEAD
			return new ProjectEntity(id, gitProjectId, gitType, repositoryName, repositoryUrl, owner, description);
		}
		return new ProjectEntity(gitProjectId, gitType, repositoryName, repositoryUrl, owner, description);
=======
			return new ProjectEntity(id, gitlabProjectId, gitType, name, repositoryUrl, owner, description);
		}
		return new ProjectEntity(gitlabProjectId, gitType, name, repositoryUrl, owner, description);
>>>>>>> main
	}

}
