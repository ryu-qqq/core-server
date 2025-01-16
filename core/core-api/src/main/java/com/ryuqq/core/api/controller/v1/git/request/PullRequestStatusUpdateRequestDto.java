package com.ryuqq.core.api.controller.v1.git.request;

import com.ryuqq.core.enums.ReviewStatus;

public record PullRequestStatusUpdateRequestDto(
	ReviewStatus reviewStatus
) {}
