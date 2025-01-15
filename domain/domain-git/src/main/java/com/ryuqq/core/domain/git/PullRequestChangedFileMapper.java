package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.dto.PullRequestChangedFileDto;

import org.springframework.stereotype.Component;

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
