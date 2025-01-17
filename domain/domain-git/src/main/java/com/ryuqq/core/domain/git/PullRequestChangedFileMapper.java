package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.dto.PullRequestChangedFileDto;

@Component
public class PullRequestChangedFileMapper {

	public PullRequestChangedFileSummary toDomain(PullRequestChangedFileDto pullRequestChangedFileDto){
		return new PullRequestChangedFileSummary(
			pullRequestChangedFileDto.getRepositoryName(),
			pullRequestChangedFileDto.getOwner(),
			pullRequestChangedFileDto.getPullRequestId(),
			pullRequestChangedFileDto.getCommitId(),
			pullRequestChangedFileDto.getChangedFileId(),
			pullRequestChangedFileDto.getFilePath(),
			pullRequestChangedFileDto.getClassName(),
			pullRequestChangedFileDto.getChangeTyp(),
			pullRequestChangedFileDto.getReviewStatus()
		);
	}
}
