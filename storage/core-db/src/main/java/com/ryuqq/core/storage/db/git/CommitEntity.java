package com.ryuqq.core.storage.db.git;

import java.time.OffsetDateTime;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMMIT")
public class CommitEntity extends BaseEntity {

	@Column(name = "BRANCH_ID", nullable = false)
	private Long branchId;

	@Column(name = "COMMIT_ID", nullable = false, length = 255)
	private String commitId;

	@Column(name = "AUTHOR", nullable = false, length = 255)
	private String author;

	@Column(name = "COMMIT_MESSAGE", nullable = false)
	private String commitMessage;

	@Column(name = "TIMESTAMP", nullable = false)
	private OffsetDateTime timestamp;

	protected CommitEntity() {}

	public CommitEntity(Long branchId, String commitId, String author, String commitMessage, OffsetDateTime timestamp) {
		this.branchId = branchId;
		this.commitId = commitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
	}

	public CommitEntity(long id, Long branchId, String commitId, String author, String commitMessage, OffsetDateTime timestamp) {
		this.id = id;
		this.branchId = branchId;
		this.commitId = commitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
	}



	public Long getBranchId() {
		return branchId;
	}

	public String getCommitId() {
		return commitId;
	}

	public String getAuthor() {
		return author;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}
}
