package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.git.ReviewExecutionCommand;

public class ReviewExecution {

	private final long commitId;
	private final long changedFileId;
	private final TestStatus testStatus;
	private final ReviewStatus reviewStatus;


	protected ReviewExecution(long commitId, long changedFileId, TestStatus testStatus, ReviewStatus reviewStatus) {
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.testStatus = testStatus;
		this.reviewStatus = reviewStatus;
	}

	public ReviewExecutionCommand toCommand(){
		return new ReviewExecutionCommand(commitId, changedFileId, testStatus, reviewStatus);
	}

	public long getCommitId() {
		return commitId;
	}

	public long getChangedFileId() {
		return changedFileId;
	}

	public TestStatus getTestStatus() {
		return testStatus;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
}
