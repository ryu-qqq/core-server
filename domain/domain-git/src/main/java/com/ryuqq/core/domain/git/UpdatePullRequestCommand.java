package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;

public record UpdatePullRequestCommand(
	Long id,
	long gitPullId,
	GitType gitType,
	long branchId,
	String sourceBranch,
	String targetBranch,
	String title,
	String description,
	MergeStatus status,
	ReviewStatus reviewStatus
)

	implements PullRequestCommand{
}
