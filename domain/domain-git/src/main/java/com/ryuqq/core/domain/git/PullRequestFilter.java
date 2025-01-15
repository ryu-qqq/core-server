package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.storage.db.git.dto.PullRequestStorageFilterDto;

public record PullRequestFilter(
	GitType gitType,
	MergeStatus status,
	ReviewStatus reviewStatus,
	Integer pageSize,
	Integer pageNumber,
	Long cursorId,
	Sort sort
) {

	public PullRequestStorageFilterDto toStorageFilterDto(){
		return new PullRequestStorageFilterDto(gitType, status, reviewStatus, pageSize, pageNumber, cursorId, sort);
	}
}
