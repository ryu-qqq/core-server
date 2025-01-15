package com.ryuqq.core.storage.db.git;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.BaseEntity;

@Table(name = "PULL_REQUEST_CHANGED_FILE")
@Entity
public class PullRequestChangedFileEntity extends BaseEntity {

	@Column(name = "PULL_REQUEST_ID", nullable = false)
	private long pullRequestId;

	@Column(name = "FILE_PATH", nullable = false, length = 255)
	private String filePath;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_TYPE", nullable = false)
	private ChangeType changeType;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private TestStatus status;

	protected PullRequestChangedFileEntity() {}

	public PullRequestChangedFileEntity(long pullRequestId, String filePath, ChangeType changeType, TestStatus status) {
		this.pullRequestId = pullRequestId;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
	}

	public PullRequestChangedFileEntity(long id, long pullRequestId, String filePath, ChangeType changeType, TestStatus status) {
		this.id = id;
		this.pullRequestId = pullRequestId;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
	}
}
