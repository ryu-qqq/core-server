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
				pair -> pair.getChangedFile().getFilePath(),
				Function.identity(),
				(existing, replacement) -> existing // Handle duplicate filePath if needed
			));
	}




	protected static class FileCommitPair {
		private final ChangedFile changedFile;
		private final Commit commit;

		public FileCommitPair(ChangedFile changedFile, Commit commit) {
			this.changedFile = changedFile;
			this.commit = commit;
		}

		public ChangedFile getChangedFile() {
			return changedFile;
		}

		public Commit getCommit() {
			return commit;
		}
	}

}
