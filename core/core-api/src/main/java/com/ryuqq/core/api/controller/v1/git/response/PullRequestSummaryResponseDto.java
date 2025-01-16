package com.ryuqq.core.api.controller.v1.git.response;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;

import java.time.LocalDateTime;

public record PullRequestSummaryResponseDto(
	long id,
	long gitPullId,
	long branchId,
	GitType gitType,
	String sourceBranch,
	String targetBranch,
	String title,
	MergeStatus status,
	ReviewStatus reviewStatus,
	LocalDateTime createAt
) {}
