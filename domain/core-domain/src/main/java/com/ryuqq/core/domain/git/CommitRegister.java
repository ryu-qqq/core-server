package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.CommitPersistenceRepository;

import org.springframework.stereotype.Component;

@Component
public class CommitRegister {

	private final CommitPersistenceRepository commitPersistenceRepository;

	public CommitRegister(CommitPersistenceRepository commitPersistenceRepository) {
		this.commitPersistenceRepository = commitPersistenceRepository;
	}

	public long save(long branchId, Commit commit) {
		return commitPersistenceRepository.save(commit.toCommand(branchId));
	}

}
