package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.storage.db.git.PullRequestCommitCommand;

public class PullRequestCommit {
	Long id;
	long pullRequestId;
	long commitId;
	String gitCommitId;
	String filePath;
	ChangeType changeType;

	public PullRequestCommit(String gitCommitId, String filePath, String changeType) {
		this.gitCommitId = gitCommitId;
		this.filePath = filePath;
		this.changeType = ChangeType.of(changeType.toUpperCase());
	}

	public PullRequestCommit(long commitId, long pullRequestId) {
		this.commitId = commitId;
		this.pullRequestId = pullRequestId;
	}

	public PullRequestCommitCommand toCommand() {
		return new PullRequestCommitCommand(pullRequestId, commitId);
	}

	public Long getId() {
		return id;
	}

	public long getPullRequestId() {
		return pullRequestId;
	}

	public long getCommitId() {
		return commitId;
	}

	public String getGitCommitId() {
		return gitCommitId;
	}

	public String getFilePath() {
		return filePath;
	}

	public ChangeType getChangeType() {
		return changeType;
	}
}
