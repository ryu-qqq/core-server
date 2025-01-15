package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.storage.db.git.PullRequestCommand;

import java.util.List;

public class PullRequest {
	Long id;
	Long branchId;
	long gitProjectId;
	long gitPullId;
	String sourceBranch;
	String targetBranch;
	String title;
	String description;
	MergeStatus status;
	List<PullRequestCommit> commits;

	public PullRequest(long gitProjectId, long gitPullId, String sourceBranch, String targetBranch, String title,
					   String description, MergeStatus status, List<PullRequestCommit> commits) {
		this.gitProjectId = gitProjectId;
		this.gitPullId = gitPullId;
		this.sourceBranch = sourceBranch;
		this.targetBranch = targetBranch;
		this.title = title;
		this.description = description;
		this.status = status;
		this.commits = commits;
	}

	public PullRequest(Long id, long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
					   String description, MergeStatus status) {
		this.id = id;
		this.gitPullId = gitPullId;
		this.branchId = branchId;
		this.sourceBranch = sourceBranch;
		this.targetBranch = targetBranch;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public PullRequestCommand toCommand(long branchId){
		return new PullRequestCommand(id, gitPullId, branchId, sourceBranch, targetBranch, title, description, status);
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

}
