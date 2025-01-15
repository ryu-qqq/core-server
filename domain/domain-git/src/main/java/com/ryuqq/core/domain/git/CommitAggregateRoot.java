package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class CommitAggregateRoot {
	private final BranchFinder branchFinder;
	private final CommitRegister commitRegister;
	private final ChangedFileRegister changedFileRegister;

	public CommitAggregateRoot(BranchFinder branchFinder, CommitRegister commitRegister, ChangedFileRegister changedFileRegister) {
		this.branchFinder = branchFinder;
		this.commitRegister = commitRegister;
		this.changedFileRegister = changedFileRegister;
	}

	@Transactional
	public long processCommits(long gitProjectId, String branchName, List<Commit> commits) {
		Branch branch = branchFinder.fetchByGitProjectIdAndBranchName(gitProjectId, branchName);
		commits.forEach(commit -> {
			long commitId = commitRegister.register(branch.getId(), commit);
			changedFileRegister.register(commitId, commit.getChangedFiles());
		});

		return branch.getId();
	}

}
