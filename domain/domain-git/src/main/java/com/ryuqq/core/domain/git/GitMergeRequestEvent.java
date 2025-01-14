package com.ryuqq.core.domain.git;

import java.util.List;

public record GitMergeRequestEvent(
	Project project,
	Branch branch,
	List<Commit> commits) {

	public long getGitProjectId(){
		return project.getGitProjectId();
	}

	public int getTotalFilesCount(){
		return commits.stream().map(c -> c.getChangedFiles().size())
			.reduce(0, Integer::sum);
	}

	@Override
	public String toString() {
		return "GitMergeRequestEvent{"
			+
			"project="
			+ project
			+
			", branch="
			+ branch
			+
			", commits="
			+ commits
			+
			'}';
	}

}
