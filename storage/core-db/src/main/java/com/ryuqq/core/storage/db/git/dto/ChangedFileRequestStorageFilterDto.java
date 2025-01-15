package com.ryuqq.core.storage.db.git.dto;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.enums.TestStatus;

public record ChangedFileRequestStorageFilterDto(
	GitType gitType,
	TestStatus testStatus,
	Integer pageSize,
	Integer pageNumber,
	Long cursorId,
	Sort sort
) {


}
