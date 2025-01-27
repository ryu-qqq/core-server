package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.CommitCommand;

@Component
public class CommitStorageMapper {


	public CommitEntity toEntity(CommitCommand commitCommand){
		if(commitCommand.id() != null){
			return new CommitEntity(
				commitCommand.id(),
				commitCommand.commitId(),
				commitCommand.gitCommitId(),
				commitCommand.author(),
				commitCommand.commitMessage(),
				commitCommand.timestamp());
		}
		return new CommitEntity(
			commitCommand.commitId(),
			commitCommand.gitCommitId(),
			commitCommand.author(),
			commitCommand.commitMessage(),
			commitCommand.timestamp());
	}
}
