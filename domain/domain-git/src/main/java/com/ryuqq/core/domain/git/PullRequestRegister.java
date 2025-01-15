package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.PullRequestPersistenceRepository;

import org.springframework.stereotype.Component;

@Component
public class PullRequestRegister {

	private final PullRequestPersistenceRepository pullRequestPersistenceRepository;

	public PullRequestRegister(PullRequestPersistenceRepository pullRequestPersistenceRepository) {
		this.pullRequestPersistenceRepository = pullRequestPersistenceRepository;
	}

	public long register(PullRequest pullRequest){
		return pullRequestPersistenceRepository.save(pullRequest.toCommand());
	}


}
