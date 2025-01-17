package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.BranchPersistenceRepository;

@Component
public class BranchRegister {

	private final BranchPersistenceRepository branchPersistenceRepository;

	public BranchRegister(BranchPersistenceRepository branchPersistenceRepository) {
		this.branchPersistenceRepository = branchPersistenceRepository;
	}

	public long register(Branch branch) {
		return branchPersistenceRepository.save(branch.toCommand());
	}

	public long register(long projectId, String branchName, String baseBranchName){
		Branch branch = BranchFactory.create(projectId, branchName, baseBranchName);
		return register(branch);
	}

}
