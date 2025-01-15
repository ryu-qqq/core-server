package com.ryuqq.core.storage.db.git;

import java.time.LocalDateTime;

public record CommitCommand(
	Long id,
	Long commitId,
	String gitCommitId,
	String author,
	String commitMessage,
	LocalDateTime timestamp
) {

	public CommitEntity toEntity(){
		if(id != null){
			return new CommitEntity(id, commitId, gitCommitId, author, commitMessage, timestamp);
		}
		return new CommitEntity(commitId, gitCommitId, author, commitMessage, timestamp);
	}
}
