package com.ryuqq.core.storage.db.git;

import java.time.LocalDateTime;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMMIT")
public class CommitEntity extends BaseEntity {

	@Column(name = "BRANCH_ID", nullable = false)
	private Long branchId;

	@Column(name = "GIT_COMMIT_ID", nullable = false, length = 255)
	private String gitCommitId;

	@Column(name = "AUTHOR", nullable = false, length = 255)
	private String author;

	@Column(name = "COMMIT_MESSAGE", nullable = false)
	private String commitMessage;

	@Column(name = "TIMESTAMP", nullable = false)
	private LocalDateTime timestamp;

	protected CommitEntity() {}

	public CommitEntity(Long branchId, String gitCommitId, String author, String commitMessage,
						LocalDateTime timestamp) {
		this.branchId = branchId;
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
	}

	public CommitEntity(long id, Long branchId, String gitCommitId, String author, String commitMessage,
						LocalDateTime timestamp) {
		this.id = id;
		this.branchId = branchId;
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
	}

	public Long getBranchId() {
		return branchId;
	}

	public String getGitCommitId() {
		return gitCommitId;
	}

	public String getAuthor() {
		return author;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
