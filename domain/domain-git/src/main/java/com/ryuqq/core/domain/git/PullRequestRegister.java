package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

@Component
public class PullRequestRegister {

	private final PullRequestPersistenceRepository pullRequestPersistenceRepository;

	public PullRequestRegister(PullRequestPersistenceRepository pullRequestPersistenceRepository) {
		this.pullRequestPersistenceRepository = pullRequestPersistenceRepository;
	}

	public long register(long branchId, PullRequest pullRequest){
		return pullRequestPersistenceRepository.save(pullRequest.toCommand(branchId));
	}

}
