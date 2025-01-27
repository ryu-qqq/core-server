package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class PullRequestAggregateRoot {

	private final BranchFinder branchFinder;
	private final PullRequestRegister pullRequestRegister;
	private final PullRequestCommitAggregateProcessor pullRequestCommitAggregateProcessor;
	private final CommitFinder commitFinder;

	public PullRequestAggregateRoot(BranchFinder branchFinder, PullRequestRegister pullRequestRegister,
									PullRequestCommitAggregateProcessor pullRequestCommitAggregateProcessor, CommitFinder commitFinder) {
		this.branchFinder = branchFinder;
		this.pullRequestRegister = pullRequestRegister;
		this.pullRequestCommitAggregateProcessor = pullRequestCommitAggregateProcessor;
		this.commitFinder = commitFinder;
	}

	public long processPullRequest(PullRequest pullRequest) {
		Branch branch = branchFinder.fetchByGitProjectIdAndBranchName(pullRequest.gitProjectId, pullRequest.sourceBranch);
		long pullRequestId = savePullRequest(branch.getId(), pullRequest);
		List<Commit> commits = findCommits(branch.getId(), pullRequest.commits);

		pullRequestCommitAggregateProcessor.process(pullRequestId, pullRequest.commits, commits);
		return pullRequestId;
	}

	private long savePullRequest(long branchId, PullRequest pullRequest) {
		return pullRequestRegister.register(branchId, pullRequest);
	}

	private List<Commit> findCommits(long branchId, List<PullRequestCommit> pullRequestCommits) {
		List<String> filePaths = pullRequestCommits.stream()
			.map(PullRequestCommit::getFilePath)
			.toList();

		return commitFinder.fetchByBranchIdAndFilePathIn(branchId, filePaths);
	}

}
