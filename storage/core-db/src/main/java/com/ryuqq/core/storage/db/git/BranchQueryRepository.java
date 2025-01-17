package com.ryuqq.core.storage.db.git;

import java.util.Optional;

import com.ryuqq.core.storage.db.git.dto.BranchDto;

public interface BranchQueryRepository {
	Optional<BranchDto> fetchByProjectIdAndBranchName(long projectId, String branchName);
	Optional<BranchDto> fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName);
}
