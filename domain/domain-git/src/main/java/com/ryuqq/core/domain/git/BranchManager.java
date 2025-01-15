package com.ryuqq.core.domain.git;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class BranchManager {

	private final BranchRegister branchRegister;
	private final BranchFinder branchFinder;

	public BranchManager(BranchRegister branchRegister, BranchFinder branchFinder) {
		this.branchRegister = branchRegister;
		this.branchFinder = branchFinder;
	}

	public long fetchOrRegisterBranch(Branch branch) {
		Optional<Branch> existingBranch = branchFinder.fetchByProjectIdAndBranchName(branch.getProjectId(), branch.getBranchName());
		if (existingBranch.isPresent()) {
			return existingBranch.get().getId();
		}
		return branchRegister.register(branch);
	}


}
