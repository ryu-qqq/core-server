package com.ryuqq.core.domain.git;

import java.util.List;

public interface CommitQueryRepository {

	List<Commit> fetchByBranchIdAndFilePathIn(long branchId, List<String> filePaths);
}
