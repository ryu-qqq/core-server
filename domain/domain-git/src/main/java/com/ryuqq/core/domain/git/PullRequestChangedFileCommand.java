package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

public interface PullRequestChangedFileCommand {
	Long id();
	long pullRequestId();
	long changedFileId();
	String filePath();
	ChangeType changeType();
	ReviewStatus reviewStatus();
}
