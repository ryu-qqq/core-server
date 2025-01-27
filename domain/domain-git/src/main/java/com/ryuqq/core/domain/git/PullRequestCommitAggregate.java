package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

@Component
public class PullRequestCommitAggregate {

	private final PullRequestCommitRegister pullRequestCommitRegister;
	private final PullRequestChangedFileRegister pullRequestChangedFileRegister;
	private final ReviewExecutionRegister reviewExecutionRegister;

	public PullRequestCommitAggregate(PullRequestCommitRegister pullRequestCommitRegister,
									  PullRequestChangedFileRegister pullRequestChangedFileRegister,
									  ReviewExecutionRegister reviewExecutionRegister) {
		this.pullRequestCommitRegister = pullRequestCommitRegister;
		this.pullRequestChangedFileRegister = pullRequestChangedFileRegister;
		this.reviewExecutionRegister = reviewExecutionRegister;
	}

	public void saveCommit(long commitId, long pullRequestId) {
		PullRequestCommit pullRequestCommit = new PullRequestCommit(commitId, pullRequestId);
		pullRequestCommitRegister.register(pullRequestCommit);
	}

	public void saveChangedFile(long pullRequestId, long changedFileId, String filePath, ChangeType changeType) {
		PullRequestChangedFile pullRequestChangedFile = PullRequestChangedFile.create(null, pullRequestId, changedFileId, filePath, changeType, ReviewStatus.PENDING);
		pullRequestChangedFileRegister.register(pullRequestChangedFile);
	}

	public void saveReviewExecution(long commitId, long changedFileId){
		reviewExecutionRegister.register(ReviewExecution.create(commitId, changedFileId, 0L, null, null));

	}
}
