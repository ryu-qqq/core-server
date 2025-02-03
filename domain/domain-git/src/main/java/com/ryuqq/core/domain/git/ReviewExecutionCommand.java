package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;

public interface ReviewExecutionCommand {
	Long id();
	long branchId();
	long changedFileId();
	TestStatus testStatus();
	ReviewStatus reviewStatus();
}
