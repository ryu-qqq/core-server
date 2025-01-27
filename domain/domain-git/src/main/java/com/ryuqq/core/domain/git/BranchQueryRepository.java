package com.ryuqq.core.domain.git;

import java.util.Optional;

public interface BranchQueryRepository {
	Optional<Branch> fetchByProjectIdAndBranchName(long projectId, String branchName);
	Branch fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName);
}
