package com.ryuqq.core.domain.git;


import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class GitEventRegistrar {

	private final BranchRegister branchRegister;
	private final ChangedFileRegister changedFileRegister;

	public GitEventRegistrar(BranchRegister branchRegister, ChangedFileRegister changedFileRegister) {
		this.branchRegister = branchRegister;
		this.changedFileRegister = changedFileRegister;
	}

	@Transactional
	public long register(GitEvent gitEvent) {
		long branchId = branchRegister.register(gitEvent.getBranch());
		changedFileRegister.saveAll(branchId, gitEvent.getChangedFiles());
		return branchId;
	}

}
