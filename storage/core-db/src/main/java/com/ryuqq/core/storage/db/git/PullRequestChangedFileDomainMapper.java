package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.PullRequestChangedFile;
import com.ryuqq.core.storage.db.git.dto.PullRequestChangedFileDto;

@Component
public class PullRequestChangedFileDomainMapper {

	public PullRequestChangedFile toDomain(PullRequestChangedFileDto pullRequestChangedFileDto){
		return null;
		// return PullRequestChangedFile.create(
		// 	pullRequestChangedFileDto.getId(),
		// 	pullRequestChangedFileDto.getPullRequestId(),
		// 	pullRequestChangedFileDto.getChangedFileId(),
		// 	pullRequestChangedFileDto.getFilePath(),
		// 	pullRequestChangedFileDto.getChangeTyp()
		// );
	}

}
