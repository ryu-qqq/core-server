package com.ryuqq.core.api.controller.v1.git.response;

import java.time.LocalDateTime;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;

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
