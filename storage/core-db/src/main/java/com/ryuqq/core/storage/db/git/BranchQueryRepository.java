package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.BranchDto;

import java.util.Optional;

public interface BranchQueryRepository {
	Optional<BranchDto> fetchByProjectIdAndBranchName(long projectId, String branchName);
	Optional<BranchDto> fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName);
}
