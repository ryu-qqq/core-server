package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BranchManager {

	private final BranchRegister branchRegister;
	private final CommitManager commitManager;

	public BranchManager(BranchRegister branchRegister, CommitManager commitManager) {
		this.branchRegister = branchRegister;
		this.commitManager = commitManager;
	}

	public long registerBranchWithCommits(long projectId, Branch branch, List<Commit> commits){
		long branchId = branchRegister.register(projectId, branch);
		commitManager.processCommits(branchId, commits);

		return branchId;
	}

}
