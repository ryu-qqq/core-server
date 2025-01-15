package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;

public record PullRequestChangedFileCommand(
	long pullRequestId,
	long changedFileId,
	String filePath,
	ChangeType changeType
) {

	public PullRequestChangedFileEntity toEntity(){
		return new PullRequestChangedFileEntity(pullRequestId, changedFileId, filePath, changeType);
	}
}
