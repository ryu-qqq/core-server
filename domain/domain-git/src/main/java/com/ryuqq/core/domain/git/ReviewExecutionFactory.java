package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;

public class ReviewExecutionFactory {

	public static ReviewExecution create(long commitId, long changedFileId){
		return new ReviewExecution(commitId, changedFileId, TestStatus.PENDING, ReviewStatus.PENDING);
	}


}
