package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.dto.ChangedFileDto;

@Component
public class ChangedFileMapper {


	public ChangedFile toDomain(ChangedFileDto changedFileDto){
		return new ChangedFile(
			changedFileDto.getId(),
			changedFileDto.getCommitId(),
			changedFileDto.getClassName(),
			changedFileDto.getFilePath(),
			changedFileDto.getChangeTyp()
		);
	}

}
