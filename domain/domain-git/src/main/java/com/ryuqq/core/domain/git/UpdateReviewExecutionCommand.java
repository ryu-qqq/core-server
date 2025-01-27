package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;

public record UpdateReviewExecutionCommand(
	Long id,
	long branchId,
	long changedFileId,
	TestStatus testStatus,
	ReviewStatus reviewStatus
) implements ReviewExecutionCommand{
}
