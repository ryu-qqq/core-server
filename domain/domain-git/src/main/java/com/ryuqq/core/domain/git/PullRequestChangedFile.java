package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.storage.db.git.PullRequestChangedFileCommand;

public class PullRequestChangedFile {

	Long id;
	long pullRequestId;
	long changedFileId;
	String filePath;
	ChangeType changeType;
	ReviewStatus reviewStatus;

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

}
