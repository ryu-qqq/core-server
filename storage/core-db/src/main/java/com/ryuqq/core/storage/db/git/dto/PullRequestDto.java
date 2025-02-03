package com.ryuqq.core.storage.db.git.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;

public class PullRequestDto {
	private long id;
	private GitType gitType;
	private long gitPullId;
	private long branchId;
	private String sourceBranch;
	private String targetBranch;
	private String title;
	private String description;
	private MergeStatus status;
	private ReviewStatus reviewStatus;
	private LocalDateTime createdAt;

	@QueryProjection
	public PullRequestDto(long id, GitType gitType, long gitPullId, long branchId, String sourceBranch,
						  String targetBranch,
						  String title, String description, MergeStatus status, ReviewStatus reviewStatus,
						  LocalDateTime createdAt) {
		this.id = id;
		this.gitType = gitType;
		this.gitPullId = gitPullId;
		this.branchId = branchId;
		this.sourceBranch = sourceBranch;
		this.targetBranch = targetBranch;
		this.title = title;
		this.description = description;
		this.status = status;
		this.reviewStatus = reviewStatus;
		this.createdAt = createdAt;
	}



	public long getId() {
		return id;
	}

	public GitType getGitType() {
		return gitType;
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

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
