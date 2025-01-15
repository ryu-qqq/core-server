package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;

public class ChangedFileFactory {

	public static ChangedFile create(String className, String filePath, ChangeType changeType) {
		return new ChangedFile(className, filePath, changeType);
	}

	public static ChangedFile create(long commitId, String className, String filePath, ChangeType changeType) {
		return new ChangedFile(commitId, className, filePath, changeType);
	}
}
