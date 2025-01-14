package com.ryuqq.core.api.data;

import java.time.OffsetDateTime;
import java.util.List;

import com.ryuqq.core.api.controller.v1.git.request.GitMergeEventRequestDto;

public class GitModuleHelper {

	public static GitMergeEventRequestDto toGitPushEventRequestDto() {
		GitMergeEventRequestDto.Commit commit1 = new GitMergeEventRequestDto.Commit(
			"1", "First commit", OffsetDateTime.parse("2025-01-08T10:00:00Z"),
			new GitMergeEventRequestDto.Author("TEST", "TEST"),
			List.of("src/main/java/MyClass.java"), List.of(), List.of()
		);

		GitMergeEventRequestDto.Commit commit2 = new GitMergeEventRequestDto.Commit(
			"2", "Second commit", OffsetDateTime.parse("2025-01-08T11:00:00Z"),
			new GitMergeEventRequestDto.Author("TEST", "TEST"),
			List.of(), List.of("src/main/java/MyClass.java"), List.of()
		);

		return new GitMergeEventRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(commit1, commit2),
			new GitMergeEventRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitMergeEventRequestDto.Label(1, "Label"))
		);
	}

	public static GitMergeEventRequestDto toGitPushEventRequestDtoWithNoCommits() {
		return new GitMergeEventRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(), // No commits
			new GitMergeEventRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitMergeEventRequestDto.Label(1, "Label"))
		);
	}

	public static GitMergeEventRequestDto toGitPushEventRequestDtoWithMixedFiles() {
		GitMergeEventRequestDto.Commit commit1 = new GitMergeEventRequestDto.Commit(
			"1", "First commit", OffsetDateTime.parse("2025-01-08T10:00:00Z"),
			new GitMergeEventRequestDto.Author("TEST", "TEST"),
			List.of("src/main/java/MyClass.java"), // Added files
			List.of("src/main/java/YourClass.java"), // Modified files
			List.of()
		);

		return new GitMergeEventRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(commit1), // Single commit with mixed files
			new GitMergeEventRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitMergeEventRequestDto.Label(1, "Label"))
		);
	}

	public static GitMergeEventRequestDto toGitPushEventRequestDtoWithEmptyFilePath() {
		GitMergeEventRequestDto.Commit commit1 = new GitMergeEventRequestDto.Commit(
			"1", "First commit", OffsetDateTime.parse("2025-01-08T10:00:00Z"),
			new GitMergeEventRequestDto.Author("TEST", "TEST"),
			List.of(""), // Added files
			List.of(), // Modified files
			List.of()
		);

		return new GitMergeEventRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(commit1), // Single commit with mixed files
			new GitMergeEventRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitMergeEventRequestDto.Label(1, "Label"))
		);
	}


}
