package com.ryuqq.core.domain.git;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;

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
	LocalDateTime createAt;
	List<PullRequestCommit> commits;

	private PullRequest(GitType gitType, long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
						String description, MergeStatus status, ReviewStatus reviewStatus, LocalDateTime createAt) {
		this.gitType = gitType;
		this.gitPullId = gitPullId;
		this.branchId = branchId;
		this.sourceBranch = sourceBranch;
		this.targetBranch = targetBranch;
		this.title = title;
		this.description = description;
		this.status = status;
		this.reviewStatus = reviewStatus;
		this.createAt = createAt;
	}

	private PullRequest(Long id, GitType gitType, long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
					   String description, MergeStatus status, ReviewStatus reviewStatus, LocalDateTime createAt) {
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
		this.createAt = createAt;
	}

	public static PullRequest create(Long id, GitType gitType, long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
									 String description, MergeStatus status, ReviewStatus reviewStatus, LocalDateTime createAt){

		return new PullRequest(id, gitType, gitPullId, branchId, sourceBranch, targetBranch, title, description, status, reviewStatus, createAt);
	}

	public PullRequestCommand toCommand(long branchId){
		if(id != null){
			return new UpdatePullRequestCommand(id, gitPullId, gitType, branchId, sourceBranch, targetBranch, title, description, status, reviewStatus);
		}
		return new CreatePullRequestCommand(gitPullId, gitType, branchId, sourceBranch, targetBranch, title, description, status, reviewStatus);
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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public List<PullRequestCommit> getCommits() {
		return commits;
	}

	public boolean isOpened(){
		return status.isOpened();
	}

}
