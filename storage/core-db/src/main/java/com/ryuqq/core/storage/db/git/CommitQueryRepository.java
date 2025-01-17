package com.ryuqq.core.storage.db.git;

import java.util.List;

import com.ryuqq.core.storage.db.git.dto.CommitDto;

public interface CommitQueryRepository {

	List<CommitDto> fetchByBranchIdAndFilePathIn(long branchId, List<String> filePaths);
}
