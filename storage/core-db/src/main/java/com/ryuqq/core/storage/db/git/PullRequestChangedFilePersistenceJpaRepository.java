package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class PullRequestChangedFilePersistenceJpaRepository implements PullRequestChangedFilePersistenceRepository {

	private final PullRequestChangedFileJpaRepository pullRequestChangedFileJpaRepository;

	public PullRequestChangedFilePersistenceJpaRepository(
		PullRequestChangedFileJpaRepository pullRequestChangedFileJpaRepository) {
		this.pullRequestChangedFileJpaRepository = pullRequestChangedFileJpaRepository;
	}

	@Override
	public void save(PullRequestChangedFileCommand pullRequestChangedFileCommand) {
		pullRequestChangedFileJpaRepository.save(pullRequestChangedFileCommand.toEntity());
	}

}
