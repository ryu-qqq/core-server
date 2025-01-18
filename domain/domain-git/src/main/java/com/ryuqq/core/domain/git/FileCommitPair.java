package com.ryuqq.core.domain.git;

public record FileCommitPair(
	ChangedFile changedFile,
	Commit commit
) {

	public String getFilePath(){
		return changedFile.getFilePath();
	}
}
