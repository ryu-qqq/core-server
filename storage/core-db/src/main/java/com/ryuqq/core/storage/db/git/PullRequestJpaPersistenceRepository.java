package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class PullRequestJpaPersistenceRepository implements PullRequestPersistenceRepository{

	private final PullRequestJpaRepository pullRequestJpaRepository;

	public PullRequestJpaPersistenceRepository(PullRequestJpaRepository pullRequestJpaRepository) {
		this.pullRequestJpaRepository = pullRequestJpaRepository;
	}

	@Override
	public long save(PullRequestCommand pullRequestCommand) {
		return pullRequestJpaRepository.save(pullRequestCommand.toEntity()).getId();
	}
}
