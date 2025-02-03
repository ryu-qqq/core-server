package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.BranchCommand;
import com.ryuqq.core.domain.git.BranchPersistenceRepository;

@Repository
public class BranchJpaPersistenceRepository implements BranchPersistenceRepository {

	private final BranchStorageMapper branchStorageMapper;
	private final BranchJpaRepository branchJpaRepository;

	public BranchJpaPersistenceRepository(BranchStorageMapper branchStorageMapper, BranchJpaRepository branchJpaRepository) {
		this.branchStorageMapper = branchStorageMapper;
		this.branchJpaRepository = branchJpaRepository;
	}

	@Override
	public long save(BranchCommand branchCommand) {
		return branchJpaRepository.save(branchStorageMapper.toEntity(branchCommand)).getId();
	}

}
