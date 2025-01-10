package com.ryuqq.core.storage.db.git;

public record BranchCommand(
	Long id,
	long projectId,
	String repositoryName,
	String repositoryUrl,
	String name,
	String baseBranch
) {

	public BranchEntity toEntity(){
		if(id != null){
			return new BranchEntity(id, projectId, repositoryName, repositoryUrl, name, baseBranch);
		}
		return new BranchEntity(projectId, repositoryName, repositoryUrl, name, baseBranch);
	}

}
