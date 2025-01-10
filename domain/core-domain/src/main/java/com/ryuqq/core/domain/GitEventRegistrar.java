package com.ryuqq.core.domain;


import jakarta.transaction.Transactional;

import org.springframework.stereotype.Component;

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
		long branchId = branchRegister.register(gitEvent.branch());
		changedFileRegister.saveAll(branchId, gitEvent.changedFiles());
		return branchId;
	}

}
