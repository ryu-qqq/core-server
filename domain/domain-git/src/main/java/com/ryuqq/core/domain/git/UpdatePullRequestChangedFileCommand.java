package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

public record UpdatePullRequestChangedFileCommand(
	Long id,
	long pullRequestId,
	long changedFileId,
	String filePath,
	ChangeType changeType,
	ReviewStatus reviewStatus
) implements PullRequestChangedFileCommand {
}
