package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class CommitJpaPersistenceRepository implements CommitPersistenceRepository {

	private final CommitJpaRepository commitJpaRepository;

	public CommitJpaPersistenceRepository(CommitJpaRepository commitJpaRepository) {
		this.commitJpaRepository = commitJpaRepository;
	}

	@Override
	public long save(CommitCommand commitCommand) {
		return commitJpaRepository.save(commitCommand.toEntity()).getId();
	}

}
