package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.Sort;

public record PullRequestFilter(
	GitType gitType,
	MergeStatus status,
	ReviewStatus reviewStatus,
	Integer pageSize,
	Integer pageNumber,
	Long cursorId,
	Sort sort
) {


}
