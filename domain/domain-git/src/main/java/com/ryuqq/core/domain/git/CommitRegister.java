package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

@Component
public class CommitRegister {

	private final CommitPersistenceRepository commitPersistenceRepository;

	public CommitRegister(CommitPersistenceRepository commitPersistenceRepository) {
		this.commitPersistenceRepository = commitPersistenceRepository;
	}

	public long register(long branchId, Commit commit) {
		return commitPersistenceRepository.save(commit.toCommand(branchId));
	}

}
