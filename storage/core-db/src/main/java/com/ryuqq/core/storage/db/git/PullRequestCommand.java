package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;

public record PullRequestCommand(
	Long id,
	long gitPullId,
	GitType gitType,
	long branchId,
	String sourceBranch,
	String targetBranch,
	String title,
	String description,
	MergeStatus status,
	ReviewStatus reviewStatus
) {

	public PullRequestEntity toEntity(){
		if(id == null){
			return new PullRequestEntity(gitPullId, branchId, gitType, sourceBranch, targetBranch, title, description, status, reviewStatus);
		}

		return new PullRequestEntity(id, gitPullId, branchId, gitType, sourceBranch, targetBranch, title, description, status, reviewStatus);
	}

}
