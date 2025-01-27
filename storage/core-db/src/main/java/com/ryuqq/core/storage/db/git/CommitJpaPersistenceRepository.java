package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.CommitCommand;
import com.ryuqq.core.domain.git.CommitPersistenceRepository;

@Repository
public class CommitJpaPersistenceRepository implements CommitPersistenceRepository {

	private final CommitStorageMapper commitStorageMapper;
	private final CommitJpaRepository commitJpaRepository;

	public CommitJpaPersistenceRepository(CommitStorageMapper commitStorageMapper, CommitJpaRepository commitJpaRepository) {
		this.commitStorageMapper = commitStorageMapper;
		this.commitJpaRepository = commitJpaRepository;
	}

	@Override
	public long save(CommitCommand commitCommand) {
		return commitJpaRepository.save(commitStorageMapper.toEntity(commitCommand)).getId();
	}

}
