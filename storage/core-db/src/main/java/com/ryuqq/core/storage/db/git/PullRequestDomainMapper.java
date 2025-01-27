package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.PullRequestCommand;

@Component
public class PullRequestDomainMapper {

	public PullRequestEntity toEntity(PullRequestCommand pullRequestCommand){
		if(pullRequestCommand != null){
			return new PullRequestEntity(
				pullRequestCommand.id(),
				pullRequestCommand.gitPullId(),
				pullRequestCommand.branchId(),
				pullRequestCommand.gitType(),
				pullRequestCommand.sourceBranch(),
				pullRequestCommand.targetBranch(),
				pullRequestCommand.title(),
				pullRequestCommand.description(),
				pullRequestCommand.status(),
				pullRequestCommand.reviewStatus()
			);
		}

		return new PullRequestEntity(
			pullRequestCommand.gitPullId(),
			pullRequestCommand.branchId(),
			pullRequestCommand.gitType(),
			pullRequestCommand.sourceBranch(),
			pullRequestCommand.targetBranch(),
			pullRequestCommand.title(),
			pullRequestCommand.description(),
			pullRequestCommand.status(),
			pullRequestCommand.reviewStatus()
		);
	}
}
