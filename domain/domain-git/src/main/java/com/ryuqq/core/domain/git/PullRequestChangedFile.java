package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

public class PullRequestChangedFile {

	private Long id;
	private long pullRequestId;
	private long changedFileId;
	private final String filePath;
	private final ChangeType changeType;
	private ReviewStatus reviewStatus;



	private PullRequestChangedFile(Long id, long pullRequestId, long changedFileId, String filePath, ChangeType changeType, ReviewStatus reviewStatus) {
		this.id = id;
		this.pullRequestId = pullRequestId;
		this.changedFileId = changedFileId;
		this.filePath = filePath;
		this.changeType = changeType;
		this.reviewStatus = reviewStatus;
	}

	public static PullRequestChangedFile create(Long id, long pullRequestId, long changedFileId, String filePath, ChangeType changeType, ReviewStatus reviewStatus) {
		return new PullRequestChangedFile(id, pullRequestId, changedFileId, filePath, changeType, reviewStatus);
	}

	public PullRequestChangedFileCommand toCommand(){
		if(id != null){
			return new UpdatePullRequestChangedFileCommand(id, pullRequestId, changedFileId, filePath, changeType, reviewStatus);
		}
		return new CreatePullRequestChangedFileCommand(pullRequestId, changedFileId, filePath, changeType, reviewStatus);

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
