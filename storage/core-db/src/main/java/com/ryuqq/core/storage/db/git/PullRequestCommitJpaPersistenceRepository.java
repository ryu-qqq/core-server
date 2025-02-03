package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.PullRequestCommitCommand;
import com.ryuqq.core.domain.git.PullRequestCommitPersistenceRepository;

@Repository
public class PullRequestCommitJpaPersistenceRepository implements PullRequestCommitPersistenceRepository {

	private final PullRequestCommitJpaRepository pullRequestCommitJpaRepository;

	public PullRequestCommitJpaPersistenceRepository(PullRequestCommitJpaRepository pullRequestCommitJpaRepository) {
		this.pullRequestCommitJpaRepository = pullRequestCommitJpaRepository;
	}

	@Override
	public void save(PullRequestCommitCommand pullRequestCommitCommand) {
		//pullRequestCommitJpaRepository.save(pullRequestCommitCommand.toEntity());
	}

}
