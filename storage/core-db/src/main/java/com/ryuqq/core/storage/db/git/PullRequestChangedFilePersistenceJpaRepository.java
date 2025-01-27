package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.PullRequestChangedFileCommand;
import com.ryuqq.core.domain.git.PullRequestChangedFilePersistenceRepository;

@Repository
public class PullRequestChangedFilePersistenceJpaRepository implements PullRequestChangedFilePersistenceRepository {

	private final PullRequestChangedFileStorageMapper pullRequestChangedFileStorageMapper;
	private final PullRequestChangedFileJpaRepository pullRequestChangedFileJpaRepository;

	public PullRequestChangedFilePersistenceJpaRepository(
		PullRequestChangedFileStorageMapper pullRequestChangedFileStorageMapper, PullRequestChangedFileJpaRepository pullRequestChangedFileJpaRepository) {
		this.pullRequestChangedFileStorageMapper = pullRequestChangedFileStorageMapper;
		this.pullRequestChangedFileJpaRepository = pullRequestChangedFileJpaRepository;
	}

	@Override
	public void save(PullRequestChangedFileCommand pullRequestChangedFileCommand) {
		pullRequestChangedFileJpaRepository.save(pullRequestChangedFileStorageMapper.toEntity(pullRequestChangedFileCommand));
	}

}
