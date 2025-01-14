package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.git.dto.ChangedFileRequestStorageFilterDto;

public record ChangedFileRequestFilter(
	GitType gitType,
	TestStatus testStatus,
	Integer pageSize,
	Integer pageNumber,
	Long cursorId,
	Sort sort
) {

	public ChangedFileRequestStorageFilterDto toStorageFilterDto(){
		return new ChangedFileRequestStorageFilterDto(
			gitType, testStatus, pageSize, pageNumber, cursorId, sort
		);
	}
}
