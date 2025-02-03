package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;

public record CreateChangedFileCommand(
	long commitId,
	String className,
	String filePath,
	ChangeType changeType
) implements ChangedFileCommand{

	@Override
	public Long id() {
		return null;
	}
}
