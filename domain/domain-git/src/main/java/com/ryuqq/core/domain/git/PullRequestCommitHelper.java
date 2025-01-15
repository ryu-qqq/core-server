package com.ryuqq.core.domain.git;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PullRequestCommitHelper {

	public Map<String, Commit> mapCommitsByGitCommitId(List<Commit> commits) {
		return commits.stream()
			.collect(Collectors.toMap(Commit::getGitCommitId, Function.identity(), (v1, v2) -> v1));
	}

	public Map<String, PullRequestCommit> mapPullRequestCommitsByGitCommitId(List<PullRequestCommit> pullRequestCommits) {
		return pullRequestCommits.stream()
			.collect(Collectors.toMap(PullRequestCommit::getGitCommitId, Function.identity()));
	}

	public Map<String, ChangedFile> mapChangedFilesByPath(List<ChangedFile> changedFiles) {
		return changedFiles.stream()
			.collect(Collectors.toMap(ChangedFile::getFilePath, Function.identity(), (v1, v2) -> v1));
	}
}
