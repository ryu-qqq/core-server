package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class PullRequestCommitJpaPersistenceRepository implements PullRequestCommitPersistenceRepository{

	private final PullRequestCommitJpaRepository pullRequestCommitJpaRepository;

	public PullRequestCommitJpaPersistenceRepository(PullRequestCommitJpaRepository pullRequestCommitJpaRepository) {
		this.pullRequestCommitJpaRepository = pullRequestCommitJpaRepository;
	}

	@Override
	public void save(PullRequestCommitCommand pullRequestCommitCommand) {
		pullRequestCommitJpaRepository.save(pullRequestCommitCommand.toEntity());
	}

}
