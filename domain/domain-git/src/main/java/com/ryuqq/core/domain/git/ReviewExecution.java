package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.git.ReviewExecutionCommand;

public class ReviewExecution {

	private long commitId;
	private long changedFileId;
	private TestStatus testStatus;
	private ReviewStatus reviewStatus;


	protected ReviewExecution(long commitId, long changedFileId, TestStatus testStatus, ReviewStatus reviewStatus) {
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.testStatus = testStatus;
		this.reviewStatus = reviewStatus;
	}

	public ReviewExecutionCommand toCommand(){
		return new ReviewExecutionCommand(commitId, changedFileId, testStatus, reviewStatus);
	}


}
