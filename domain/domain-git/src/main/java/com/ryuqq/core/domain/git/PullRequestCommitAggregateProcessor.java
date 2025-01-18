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

		commits.forEach(c -> commitAggregate.saveCommit(c.getId(), pullRequestId));

		Map<String, FileCommitPair> fileCommitMap =  pullRequestCommitHelper.mapPullRequestCommitsByGitCommitId(commits);

		pullRequestCommits.forEach(pullRequestCommit -> {
			String filePath = pullRequestCommit.getFilePath();
			FileCommitPair fileCommitPair = fileCommitMap.get(filePath);

			if (fileCommitPair != null) {
				ChangedFile changedFile = fileCommitPair.changedFile();
				Commit commit = fileCommitPair.commit();

				commitAggregate.saveChangedFile(pullRequestId, changedFile.getId(), filePath, pullRequestCommit.getChangeType());
				commitAggregate.saveReviewExecution(commit.getId(), changedFile.getId());
			}
		});
	}


}
