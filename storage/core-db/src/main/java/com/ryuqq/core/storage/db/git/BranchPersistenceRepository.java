package com.ryuqq.core.storage.db.git;

public interface BranchPersistenceRepository {

	long save(BranchCommand branchCommand);

}
