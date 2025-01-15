package com.ryuqq.core.storage.db.git;

public record BranchCommand(
	Long id,
	long projectId,
	String branchName,
	String baseBranchName
) {

	public BranchEntity toEntity(){
		if(id != null){
			return new BranchEntity(id, projectId, branchName, baseBranchName);
		}
		return new BranchEntity(projectId, branchName, baseBranchName);
	}

}
