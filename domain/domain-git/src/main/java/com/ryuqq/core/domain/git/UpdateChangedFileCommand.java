package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;

public record UpdateChangedFileCommand(
	Long id,
	long commitId,
	String className,
	String filePath,
	ChangeType changeType
) implements ChangedFileCommand{
}
