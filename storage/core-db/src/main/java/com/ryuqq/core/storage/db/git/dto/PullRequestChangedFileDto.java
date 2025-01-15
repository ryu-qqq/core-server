package com.ryuqq.core.storage.db.git.dto;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

import com.querydsl.core.annotations.QueryProjection;

public class PullRequestChangedFileDto {

	private final String repositoryName;
	private final String owner;
	long pullRequestId;
	long commitId;
	long changedFileId;
	private final String className;
	private final String filePath;
	private final ChangeType changeTyp;
	private final ReviewStatus reviewStatus;


	@QueryProjection
	public PullRequestChangedFileDto(String repositoryName, String owner, long pullRequestId, long commitId, long changedFileId, String className,
									 String filePath, ChangeType changeTyp, ReviewStatus reviewStatus) {
		this.repositoryName = repositoryName;
		this.owner = owner;
		this.pullRequestId = pullRequestId;
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.className = className;
		this.filePath = filePath;
		this.changeTyp = changeTyp;
		this.reviewStatus = reviewStatus;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public String getOwner() {
		return owner;
	}

	public long getPullRequestId() {
		return pullRequestId;
	}

	public long getCommitId() {
		return commitId;
	}

	public long getChangedFileId() {
		return changedFileId;
	}

	public String getClassName() {
		return className;
	}

	public String getFilePath() {
		return filePath;
	}

	public ChangeType getChangeTyp() {
		return changeTyp;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
}
