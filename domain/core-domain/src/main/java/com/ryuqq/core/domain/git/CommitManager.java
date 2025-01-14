package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommitManager {

	private final CommitRegister commitRegister;
	private final ChangedFileRegister changedFileRegister;

	public CommitManager(CommitRegister commitRegister, ChangedFileRegister changedFileRegister) {
		this.commitRegister = commitRegister;
		this.changedFileRegister = changedFileRegister;
	}

	public void processCommits(long branchId, List<Commit> commits) {
		commits.forEach(commit -> {
			long commitId = commitRegister.save(branchId, commit);
			changedFileRegister.register(commitId, commit.getChangedFiles());
		});
	}

}
