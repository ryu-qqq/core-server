package com.ryuqq.core.storage.db.git;

public record ProjectCommand (
	Long id,
	Long gitlabProjectId,
	String name,
	String repositoryUrl,
	String owner,
	String description
) {
	public ProjectEntity toEntity(){
		if(id != null){
			return new ProjectEntity(id, name, repositoryUrl, owner, description);
		}
		return new ProjectEntity(gitlabProjectId, name, repositoryUrl, owner, description);
	}

}
