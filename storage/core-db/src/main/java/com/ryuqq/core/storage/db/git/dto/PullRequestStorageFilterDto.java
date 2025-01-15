package com.ryuqq.core.storage.db.git.dto;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.Sort;

public record PullRequestStorageFilterDto(
	GitType gitType,
	MergeStatus status,
	ReviewStatus reviewStatus,
	Integer pageSize,
	Integer pageNumber,
	Long cursorId,
	Sort sort
) {}
