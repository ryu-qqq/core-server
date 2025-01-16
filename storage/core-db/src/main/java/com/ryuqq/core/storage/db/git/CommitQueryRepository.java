package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.CommitDto;

import java.util.List;

public interface CommitQueryRepository {

	List<CommitDto> fetchByBranchIdAndFilePathIn(long branchId, List<String> filePaths);
}
