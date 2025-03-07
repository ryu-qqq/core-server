package com.ryuqq.core.api.controller.v1.git.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.git.request.GitHubCommit;
import com.ryuqq.core.domain.git.Commit;

@Component
public class GitCommitCreateAdapter {

	private final GitChangedFileCreateAdapter gitChangedFileCreateAdapter;

	public GitCommitCreateAdapter(GitChangedFileCreateAdapter gitChangedFileCreateAdapter) {
		this.gitChangedFileCreateAdapter = gitChangedFileCreateAdapter;
	}

	public List<Commit> createCommitRequestDto(List<GitHubCommit> commits) {
		return commits.stream()
			.sorted((c1, c2) -> c2.getCreateAt().compareTo(c1.getCreateAt()))
			.map(commit -> Commit.create(
				commit.gitCommitId(),
				commit.getAuthor(),
				commit.commitMessage(),
				commit.getCreateAt(),
				gitChangedFileCreateAdapter.toChangedFileDomain(commit.added(), commit.removed(), commit.modified())
			))
			.toList();
	}

}
