package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

public record PullRequestChangedFileSummary(
	String repositoryName,
	String owner,
	long pullRequestId,
	long commitId,
	long changedFileId,
	String className,
	String filePath,
	ChangeType changeTyp,
	ReviewStatus reviewStatus
) {
}
