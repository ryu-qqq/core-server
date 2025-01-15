package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.PullRequestCommitPersistenceRepository;

import org.springframework.stereotype.Component;

@Component
public class PullRequestCommitRegister {

	private final PullRequestCommitPersistenceRepository pullRequestCommitPersistenceRepository;

	public PullRequestCommitRegister(PullRequestCommitPersistenceRepository pullRequestCommitPersistenceRepository) {
		this.pullRequestCommitPersistenceRepository = pullRequestCommitPersistenceRepository;
	}

	public void register(PullRequestCommit pullRequestCommit) {
		pullRequestCommitPersistenceRepository.save(pullRequestCommit.toCommand());
	}
}
