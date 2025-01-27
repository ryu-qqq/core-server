package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "PULL_REQUEST_CHANGED_FILE")
@Entity
public class PullRequestChangedFileEntity extends BaseEntity {

	@Column(name = "PULL_REQUEST_ID", nullable = false)
	private long pullRequestId;

	@Column(name = "CHANGED_FILE_ID", nullable = false)
	private long changedFileId;

	@Column(name = "FILE_PATH", nullable = false, length = 255)
	private String filePath;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_TYPE", nullable = false)
	private ChangeType changeType;

	@Enumerated(EnumType.STRING)
	@Column(name = "REVIEW_STATUS", nullable = false)
	private ReviewStatus reviewStatus;


	protected PullRequestChangedFileEntity() {}

	public PullRequestChangedFileEntity(long pullRequestId, long changedFileId, String filePath, ChangeType changeType,
										ReviewStatus reviewStatus) {
		this.pullRequestId = pullRequestId;
		this.changedFileId = changedFileId;
		this.filePath = filePath;
		this.changeType = changeType;
		this.reviewStatus = reviewStatus;
	}

	public PullRequestChangedFileEntity(long id, long pullRequestId, long changedFileId, String filePath, ChangeType changeType,
										ReviewStatus reviewStatus) {
		this.id = id;
		this.changedFileId = changedFileId;
		this.reviewStatus = reviewStatus;
		this.pullRequestId = pullRequestId;
		this.filePath = filePath;
		this.changeType = changeType;
	}
}
