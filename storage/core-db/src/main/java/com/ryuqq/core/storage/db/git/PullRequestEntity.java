package com.ryuqq.core.storage.db.git;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.storage.db.BaseEntity;

@Table(name = "PULL_REQUEST")
@Entity
public class PullRequestEntity extends BaseEntity {

	@Column(name = "GIT_PULL_ID", nullable = false)
	private long gitPullId;

	@Column(name = "BRANCH_ID", nullable = false)
	long branchId;

	@Column(name = "SOURCE_BRANCH", nullable = false, length = 255)
	private String sourceBranch;

	@Column(name = "TARGET_BRANCH", nullable = false, length = 255)
	private String targetBranch;

	@Column(name = "TITLE", nullable = false, length = 255)
	private String title;

	@Lob
	@Column(name = "DECRIPTION", nullable = true)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private MergeStatus status;

	@Column(name = "REVIEWER", nullable = false, length = 255)
	private String reviewer;

	protected PullRequestEntity() {}

	public PullRequestEntity(long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
							 String description, MergeStatus status, String reviewer) {
		this.gitPullId = gitPullId;
		this.branchId = branchId;
		this.sourceBranch = sourceBranch;
		this.targetBranch = targetBranch;
		this.title = title;
		this.description = description;
		this.status = status;
		this.reviewer = reviewer;
	}

	public PullRequestEntity(long id, long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
							 String description, MergeStatus status, String reviewer) {
		this.id = id;
		this.gitPullId = gitPullId;
		this.branchId = branchId;
		this.sourceBranch = sourceBranch;
		this.targetBranch = targetBranch;
		this.title = title;
		this.description = description;
		this.status = status;
		this.reviewer = reviewer;
	}

	public long getGitPullId() {
		return gitPullId;
	}

	public long getBranchId() {
		return branchId;
	}

	public String getSourceBranch() {
		return sourceBranch;
	}

	public String getTargetBranch() {
		return targetBranch;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public MergeStatus getStatus() {
		return status;
	}

	public String getReviewer() {
		return reviewer;
	}
}
