package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.storage.db.git.PullRequestChangedFileCommand;

public class PullRequestChangedFile {

	Long id;
	long pullRequestId;
	long changedFileId;
	String filePath;
	ChangeType changeType;

	public PullRequestChangedFile(String filePath, ChangeType changeType) {
		this.filePath = filePath;
		this.changeType = changeType;
	}

	public PullRequestChangedFile(long pullRequestId, long changedFileId, String filePath, ChangeType changeType) {
		this.pullRequestId = pullRequestId;
		this.changedFileId = changedFileId;
		this.filePath = filePath;
		this.changeType = changeType;
	}

	public PullRequestChangedFileCommand toCommand(){
		return new PullRequestChangedFileCommand(pullRequestId, changedFileId, filePath, changeType);
	}

}
