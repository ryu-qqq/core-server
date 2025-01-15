package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.storage.db.git.PullRequestCommand;

import java.util.List;

public class PullRequest {
	Long id;
	Long branchId;
	GitType gitType;
	long gitProjectId;
	long gitPullId;
	String sourceBranch;
	String targetBranch;
	String title;
	String description;
	MergeStatus status;
	ReviewStatus reviewStatus;
	List<PullRequestCommit> commits;

	public PullRequest(long gitProjectId, GitType gitType, long gitPullId, String sourceBranch, String targetBranch, String title,
					   String description, MergeStatus status, ReviewStatus reviewStatus, List<PullRequestCommit> commits) {
		this.id = 0L;
		this.branchId = 0L;
		this.gitType = gitType;
		this.gitProjectId = gitProjectId;
		this.gitPullId = gitPullId;
		this.sourceBranch = sourceBranch;
		this.targetBranch = targetBranch;
		this.title = title;
		this.description = description;
		this.status = status;
		this.reviewStatus = reviewStatus;
		this.commits = commits;
	}

	public PullRequest(Long id, GitType gitType, long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
					   String description, MergeStatus status, ReviewStatus reviewStatus) {
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

	}

	public PullRequestCommand toCommand(long branchId){
		return new PullRequestCommand(id, gitPullId, gitType, branchId, sourceBranch, targetBranch, title, description, status, reviewStatus);
	}

	public Long getId() {
		return id;
	}

	public long getGitPullId() {
		return gitPullId;
	}

	public long getBranchId() {
		return branchId;
	}

	public GitType getGitType() {
		return gitType;
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

	public long getGitProjectId() {
		return gitProjectId;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public List<PullRequestCommit> getCommits() {
		return commits;
	}

	public boolean isOpened(){
		return status.isOpened();
	}

}
