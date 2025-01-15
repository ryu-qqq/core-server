package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

import java.util.List;

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

	@Transactional
	public long processPullRequest(PullRequest pullRequest) {
		Branch branch = branchFinder.fetchByGitProjectIdAndBranchName(pullRequest.gitProjectId, pullRequest.sourceBranch);
		long pullRequestId = savePullRequest(branch.getId(), pullRequest);
		List<Commit> commits = findCommits(pullRequest.commits);

		pullRequestCommitAggregateProcessor.process(pullRequestId, pullRequest.commits, commits);
		return pullRequestId;
	}

	private long savePullRequest(long branchId, PullRequest pullRequest) {
		return pullRequestRegister.register(branchId, pullRequest);
	}

	private List<Commit> findCommits(List<PullRequestCommit> pullRequestCommits) {
		List<String> gitCommitIds = pullRequestCommits.stream()
			.map(PullRequestCommit::getGitCommitId)
			.toList();
		return commitFinder.fetchByGitCommitIdIn(gitCommitIds);
	}

}
