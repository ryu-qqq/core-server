package com.ryuqq.core.domain.git;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PullRequestCommitHelper {

	public Map<String, FileCommitPair> mapPullRequestCommitsByGitCommitId(List<Commit> commits) {
		return commits.stream()
			.flatMap(commit -> commit.getChangedFiles().stream()
				.map(changedFile -> new FileCommitPair(changedFile, commit)))
			.collect(Collectors.toMap(
				pair -> pair.getFilePath(),
				Function.identity(),
				(existing, replacement) -> existing // Handle duplicate filePath if needed
			));
	}

}
