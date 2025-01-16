package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.storage.db.git.PullRequestChangedFileCommand;

public class PullRequestChangedFile {

	private Long id;
	private long pullRequestId;
	private long changedFileId;
	private final String filePath;
	private final ChangeType changeType;
	private ReviewStatus reviewStatus;

	public PullRequestChangedFile(String filePath, ChangeType changeType) {
		this.filePath = filePath;
		this.changeType = changeType;
	}

	public PullRequestChangedFile(long pullRequestId, long changedFileId, String filePath, ChangeType changeType, ReviewStatus reviewStatus) {
		this.pullRequestId = pullRequestId;
		this.changedFileId = changedFileId;
		this.filePath = filePath;
		this.changeType = changeType;
		this.reviewStatus = reviewStatus;
	}

	public PullRequestChangedFileCommand toCommand(){
		return new PullRequestChangedFileCommand(pullRequestId, changedFileId, filePath, changeType, reviewStatus);
	}

	public Long getId() {
		return id;
	}

	public long getPullRequestId() {
		return pullRequestId;
	}

	public long getChangedFileId() {
		return changedFileId;
	}

	public String getFilePath() {
		return filePath;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
}
