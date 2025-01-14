package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;

public record ReviewExecutionCommand(
	long branchId,
	long changedFileId,
	TestStatus testStatus,
	ReviewStatus reviewStatus
) {

	public ReviewExecutionEntity toEntity(){
		return new ReviewExecutionEntity(branchId, changedFileId, testStatus, reviewStatus);
	}

}
