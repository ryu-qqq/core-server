package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "PULL_REQUEST_COMMIT")
@Entity
public class PullRequestCommitEntity extends BaseEntity {

	@Column(name = "PULL_REQUEST_ID", nullable = false)
	private long pullRequestId;

	@Column(name = "COMMIT_ID", nullable = false)
	long commitId;

	protected PullRequestCommitEntity() {}

	public PullRequestCommitEntity(long pullRequestId, long commitId) {
		this.pullRequestId = pullRequestId;
		this.commitId = commitId;
	}

	public PullRequestCommitEntity(long id, long pullRequestId, long commitId) {
		this.id = id;
		this.pullRequestId = pullRequestId;
		this.commitId = commitId;
	}

	public long getPullRequestId() {
		return pullRequestId;
	}

	public long getCommitId() {
		return commitId;
	}
}
