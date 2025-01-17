package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;

public record ChangedFileCommand(
	Long id,
	long commitId,
	String className,
	String filePath,
	ChangeType changeType
) {

	public ChangedFileEntity toEntity(){
		if(id != null){
			return new ChangedFileEntity(id, commitId, className, filePath, changeType);
		}
		return new ChangedFileEntity(commitId, className, filePath, changeType);
	}

}
