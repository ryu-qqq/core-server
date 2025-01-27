package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.ChangedFileCommand;

@Component
public class ChangedFileStorageMapper {


	public ChangedFileEntity toEntity(ChangedFileCommand changedFileCommand){
		if(changedFileCommand.id() != null){
			return new ChangedFileEntity(
				changedFileCommand.id(),
				changedFileCommand.commitId(),
				changedFileCommand.className(),
				changedFileCommand.filePath(),
				changedFileCommand.changeType()
			);
		}

		return new ChangedFileEntity(
			changedFileCommand.commitId(),
			changedFileCommand.className(),
			changedFileCommand.filePath(),
			changedFileCommand.changeType()
		);
	}

}
