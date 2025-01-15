package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;

public record PullRequestChangedFileCommand(
	long pullRequestId,
	String filePath,
	ChangeType changeType,
	TestStatus status
) {
}
