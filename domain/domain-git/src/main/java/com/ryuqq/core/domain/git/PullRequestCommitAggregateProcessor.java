package com.ryuqq.core.domain.git;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PullRequestCommitAggregateProcessor {

	private final PullRequestCommitHelper pullRequestCommitHelper;
	private final PullRequestCommitAggregate commitAggregate;

	public PullRequestCommitAggregateProcessor(PullRequestCommitHelper pullRequestCommitHelper,
											   PullRequestCommitAggregate commitAggregate) {
		this.pullRequestCommitHelper = pullRequestCommitHelper;
		this.commitAggregate = commitAggregate;
	}

	public void process(long pullRequestId, List<PullRequestCommit> pullRequestCommits, List<Commit> commits) {
		Map<String, Commit> commitMap = pullRequestCommitHelper.mapCommitsByGitCommitId(commits);
		Map<String, PullRequestCommit> pullRequestCommitMap = pullRequestCommitHelper.mapPullRequestCommitsByGitCommitId(pullRequestCommits);

		for (String gitCommitId : pullRequestCommitMap.keySet()) {
			Commit commit = commitMap.get(gitCommitId);
			PullRequestCommit pullRequestCommit = pullRequestCommitMap.get(gitCommitId);

			commitAggregate.saveCommit(commit.getId(), pullRequestId);

			Map<String, ChangedFile> changedFileMap = pullRequestCommitHelper.mapChangedFilesByPath(commit.getChangedFiles());
			ChangedFile changedFile = changedFileMap.get(pullRequestCommit.filePath);

			commitAggregate.saveChangedFile(pullRequestId, changedFile.getId(), pullRequestCommit.filePath, pullRequestCommit.changeType);

			commitAggregate.saveReviewExecution(commit.getId(), changedFile.getId());
		}
	}

}
