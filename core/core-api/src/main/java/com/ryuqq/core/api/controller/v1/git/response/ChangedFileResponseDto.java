package com.ryuqq.core.api.controller.v1.git.response;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.TestStatus;

public record ChangedFileResponseDto(
	long changedFileId,
	String className,
	String filePath,
	GitType gitType,
	ChangeType changeType
) {

}
