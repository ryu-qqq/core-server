package com.ryuqq.core.domain.git;

public interface BranchPersistenceRepository {
	long save(BranchCommand branchCommand);
}
