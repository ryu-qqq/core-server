package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;

public class ReviewExecution {
	private final Long id;
	private final long commitId;
	private final long changedFileId;
	private final TestStatus testStatus;
	private final ReviewStatus reviewStatus;


	private ReviewExecution(Long id, long commitId, long changedFileId, TestStatus testStatus, ReviewStatus reviewStatus) {
		this.id = id;
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.testStatus = testStatus;
		this.reviewStatus = reviewStatus;
	}

	public static ReviewExecution create(Long id, long commitId, long changedFileId, TestStatus testStatus, ReviewStatus reviewStatus) {
		return new ReviewExecution(id, commitId, changedFileId, testStatus, reviewStatus);
	}

	public ReviewExecutionCommand toCommand(){
		return null;
	}

	public Long getId() {
		return id;
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
