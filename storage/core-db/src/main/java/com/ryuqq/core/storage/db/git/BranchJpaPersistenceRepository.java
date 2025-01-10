package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class BranchJpaPersistenceRepository implements BranchPersistenceRepository {

	private final BranchJpaRepository branchJpaRepository;

	public BranchJpaPersistenceRepository(BranchJpaRepository branchJpaRepository) {
		this.branchJpaRepository = branchJpaRepository;
	}

	@Override
	public long save(BranchCommand branchCommand) {
		BranchEntity entity = branchCommand.toEntity();
		return branchJpaRepository.save(entity).getId();
	}

}
