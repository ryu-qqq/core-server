package com.ryuqq.core.domain.git;

import java.time.LocalDateTime;
import java.util.List;

public class CommitFactory {

	public static Commit create(String gitCommitId, String author, String commitMessage, LocalDateTime timestamp,
								List<ChangedFile> changedFiles){

		return new Commit(gitCommitId, author, commitMessage, timestamp, changedFiles);
	}

	public static Commit create(long branchId, String gitCommitId, String author, String commitMessage, LocalDateTime timestamp, List<ChangedFile> changedFiles) {
		return new Commit(branchId, gitCommitId, author, commitMessage, timestamp, changedFiles);
	}




}
