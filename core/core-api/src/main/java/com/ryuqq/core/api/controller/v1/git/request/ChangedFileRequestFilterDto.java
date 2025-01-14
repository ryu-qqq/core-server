package com.ryuqq.core.api.controller.v1.git.request;

import com.ryuqq.core.domain.git.ChangedFileRequestFilter;
import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.enums.TestStatus;

public record ChangedFileRequestFilterDto(
	GitType gitType,
	TestStatus testStatus,
	Integer pageSize,
	Integer pageNumber,
	Long cursorId,
	Sort sort

) {

	public ChangedFileRequestFilter toReviewExecutionRequestFilter() {
		int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
		int defaultNumber = (pageNumber == null || pageNumber == 0) ? 0 : pageNumber;
		return new ChangedFileRequestFilter(gitType, testStatus, defaultSize, defaultNumber, cursorId, sort);
	}

	public int getPageSize(){
		if(pageSize == null){
			return 20;
		}
		return pageSize;
	}

}
