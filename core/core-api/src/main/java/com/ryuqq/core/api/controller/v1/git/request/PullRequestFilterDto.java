package com.ryuqq.core.api.controller.v1.git.request;

import com.ryuqq.core.domain.git.PullRequestFilter;
import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.Sort;

public record PullRequestFilterDto(
	GitType gitType,
	MergeStatus status,
	ReviewStatus reviewStatus,
	Integer pageSize,
	Integer pageNumber,
	Long cursorId,
	Sort sort
) {

	public PullRequestFilter toPullRequestFilter(){
		int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
		int defaultNumber = (pageNumber == null || pageNumber == 0) ? 0 : pageNumber;

		return new PullRequestFilter(gitType, status, reviewStatus, defaultSize, defaultNumber, cursorId, sort);
	}

	public int getPageSize(){
		if(pageSize == null){
			return 20;
		}
		return pageSize;
	}

}
