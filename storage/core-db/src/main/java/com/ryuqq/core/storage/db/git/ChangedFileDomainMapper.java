package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.ChangedFile;
import com.ryuqq.core.storage.db.git.dto.ChangedFileDto;

@Component
public class ChangedFileDomainMapper {


	public ChangedFile toDomain(ChangedFileDto changedFileDto){
		return ChangedFile.create(
			changedFileDto.getId(),
			changedFileDto.getCommitId(),
			changedFileDto.getClassName(),
			changedFileDto.getFilePath(),
			changedFileDto.getChangeTyp()
		);
	}

}
