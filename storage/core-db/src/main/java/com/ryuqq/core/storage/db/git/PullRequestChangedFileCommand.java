package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

public record PullRequestChangedFileCommand(
	long pullRequestId,
	long changedFileId,
	String filePath,
	ChangeType changeType,
	ReviewStatus reviewStatus
) {

	public PullRequestChangedFileEntity toEntity(){
		return new PullRequestChangedFileEntity(pullRequestId, changedFileId, filePath, changeType, reviewStatus);
	}
}
