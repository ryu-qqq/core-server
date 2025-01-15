package com.ryuqq.core.api.controller.v1.git.response;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.ReviewStatus;

public record PullRequestChangedFileResponseDto(

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
