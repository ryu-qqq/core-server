package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.MergeStatus;

public record PullRequestCommand(
	Long id,
	long gitPullId,
	long branchId,
	String sourceBranch,
	String targetBranch,
	String title,
	String description,
	MergeStatus status
) {

	public PullRequestEntity toEntity(){
		if(id == null){
			return new PullRequestEntity(gitPullId, branchId, sourceBranch, targetBranch, title, description, status);
		}

		return new PullRequestEntity(id, gitPullId, branchId, sourceBranch, targetBranch, title, description, status);
	}

}
