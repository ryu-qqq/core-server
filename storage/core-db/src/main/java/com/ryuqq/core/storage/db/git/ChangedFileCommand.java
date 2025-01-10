package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.CodeStatus;

public record ChangedFileCommand(
	Long id,
	long branchId,
	String className,
	String filePath,
	ChangeType changeType,
	CodeStatus status,
	String commitId,
	String commitMessage
) {

	public ChangedFileEntity toEntity(){
		if(id != null){
			return new ChangedFileEntity(id, branchId, className, filePath, changeType, status, commitId, commitMessage);
		}
		return new ChangedFileEntity(branchId, className, filePath, changeType, status, commitId, commitMessage);
	}

}
