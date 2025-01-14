package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;

public record ChangedFileCommand(
	Long id,
	long commitId,
	String gitCommitId,
	String className,
	String filePath,
	ChangeType changeType,
	TestStatus status
) {

	public ChangedFileEntity toEntity(){
		if(id != null){
			return new ChangedFileEntity(id,  commitId, gitCommitId, className, filePath, changeType, status);
		}
		return new ChangedFileEntity(commitId, gitCommitId, className, filePath, changeType, status);
	}

}
