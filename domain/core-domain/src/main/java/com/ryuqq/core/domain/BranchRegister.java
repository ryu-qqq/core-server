package com.ryuqq.core.domain;

import com.ryuqq.core.storage.db.git.BranchPersistenceRepository;

import org.springframework.stereotype.Component;

@Component
public class BranchRegister {

	private final BranchPersistenceRepository branchPersistenceRepository;

	public BranchRegister(BranchPersistenceRepository branchPersistenceRepository) {
		this.branchPersistenceRepository = branchPersistenceRepository;
	}

	public long register(Branch branch) {
		return branchPersistenceRepository.save(branch.toCommand());
	}

}
