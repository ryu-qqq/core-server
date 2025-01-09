package com.ryuqq.core.domain;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.CodeStatus;

public record ChangedFileCommand(
	String className,
	String filePath,
	ChangeType changeType,
	CodeStatus status,
	String commitId,
	String commitMessage
) {
}
