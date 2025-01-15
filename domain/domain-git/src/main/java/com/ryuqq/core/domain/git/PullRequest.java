package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.storage.db.git.PullRequestCommand;

import java.util.Objects;

public class PullRequest {
	Long id;
	long gitPullId;
	long branchId;
	String sourceBranch;
	String targetBranch;
	String title;
	String description;
	MergeStatus status;
	String reviewer;

	public PullRequest(Long id, long gitPullId, long branchId, String sourceBranch, String targetBranch, String title,
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

	public PullRequestCommand toCommand(){
		return new PullRequestCommand(id, gitPullId, branchId, sourceBranch, targetBranch, title, description, status, reviewer);
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

	public String getReviewer() {
		return reviewer;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		PullRequest that = (PullRequest) object;
		return gitPullId
			== that.gitPullId
			&& branchId
			== that.branchId
			&& Objects.equals(id, that.id)
			&& Objects.equals(sourceBranch, that.sourceBranch)
			&& Objects.equals(targetBranch, that.targetBranch)
			&& Objects.equals(title, that.title)
			&& Objects.equals(description, that.description)
			&& status
			== that.status
			&& Objects.equals(reviewer, that.reviewer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, gitPullId, branchId, sourceBranch, targetBranch, title, description, status, reviewer);
	}

	@Override
	public String toString() {
		return "PullRequest{"
			+
			"id="
			+ id
			+
			", gitPullId="
			+ gitPullId
			+
			", branchId="
			+ branchId
			+
			", sourceBranch='"
			+ sourceBranch
			+ '\''
			+
			", targetBranch='"
			+ targetBranch
			+ '\''
			+
			", title='"
			+ title
			+ '\''
			+
			", description='"
			+ description
			+ '\''
			+
			", status="
			+ status
			+
			", reviewer='"
			+ reviewer
			+ '\''
			+
			'}';
	}
}
