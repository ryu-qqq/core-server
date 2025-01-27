package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.PullRequestChangedFileCommand;

@Component
public class PullRequestChangedFileStorageMapper {

	public PullRequestChangedFileEntity toEntity(PullRequestChangedFileCommand pullRequestChangedFileCommand){
		if(pullRequestChangedFileCommand.id() != null){
			return new PullRequestChangedFileEntity(
				pullRequestChangedFileCommand.id(),
				pullRequestChangedFileCommand.pullRequestId(),
				pullRequestChangedFileCommand.changedFileId(),
				pullRequestChangedFileCommand.filePath(),
				pullRequestChangedFileCommand.changeType(),
				pullRequestChangedFileCommand.reviewStatus()
			);
		}

		return new PullRequestChangedFileEntity(
			pullRequestChangedFileCommand.pullRequestId(),
			pullRequestChangedFileCommand.changedFileId(),
			pullRequestChangedFileCommand.filePath(),
			pullRequestChangedFileCommand.changeType(),
			pullRequestChangedFileCommand.reviewStatus()
		);
	}
}
