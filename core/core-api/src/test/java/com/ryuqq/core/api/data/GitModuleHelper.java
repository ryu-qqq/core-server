package com.ryuqq.core.api.data;

import java.time.OffsetDateTime;
import java.util.List;

import com.ryuqq.core.api.controller.v1.git.request.GitLabMergeEventWebhookRequestDto;

public class GitModuleHelper {

	public static GitLabMergeEventWebhookRequestDto toGitPushEventRequestDto() {
		GitLabMergeEventWebhookRequestDto.Commit commit1 = new GitLabMergeEventWebhookRequestDto.Commit(
			"1", "First commit", OffsetDateTime.parse("2025-01-08T10:00:00Z"),
			new GitLabMergeEventWebhookRequestDto.Author("TEST", "TEST"),
			List.of("src/main/java/MyClass.java"), List.of(), List.of()
		);

		GitLabMergeEventWebhookRequestDto.Commit commit2 = new GitLabMergeEventWebhookRequestDto.Commit(
			"2", "Second commit", OffsetDateTime.parse("2025-01-08T11:00:00Z"),
			new GitLabMergeEventWebhookRequestDto.Author("TEST", "TEST"),
			List.of(), List.of("src/main/java/MyClass.java"), List.of()
		);

		return new GitLabMergeEventWebhookRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(commit1, commit2),
			new GitLabMergeEventWebhookRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitLabMergeEventWebhookRequestDto.Label(1, "Label"))
		);
	}

	public static GitLabMergeEventWebhookRequestDto toGitPushEventRequestDtoWithNoCommits() {
		return new GitLabMergeEventWebhookRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(), // No commits
			new GitLabMergeEventWebhookRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitLabMergeEventWebhookRequestDto.Label(1, "Label"))
		);
	}

	public static GitLabMergeEventWebhookRequestDto toGitPushEventRequestDtoWithMixedFiles() {
		GitLabMergeEventWebhookRequestDto.Commit commit1 = new GitLabMergeEventWebhookRequestDto.Commit(
			"1", "First commit", OffsetDateTime.parse("2025-01-08T10:00:00Z"),
			new GitLabMergeEventWebhookRequestDto.Author("TEST", "TEST"),
			List.of("src/main/java/MyClass.java"), // Added files
			List.of("src/main/java/YourClass.java"), // Modified files
			List.of()
		);

		return new GitLabMergeEventWebhookRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(commit1), // Single commit with mixed files
			new GitLabMergeEventWebhookRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitLabMergeEventWebhookRequestDto.Label(1, "Label"))
		);
	}

	public static GitLabMergeEventWebhookRequestDto toGitPushEventRequestDtoWithEmptyFilePath() {
		GitLabMergeEventWebhookRequestDto.Commit commit1 = new GitLabMergeEventWebhookRequestDto.Commit(
			"1", "First commit", OffsetDateTime.parse("2025-01-08T10:00:00Z"),
			new GitLabMergeEventWebhookRequestDto.Author("TEST", "TEST"),
			List.of(""), // Added files
			List.of(), // Modified files
			List.of()
		);

		return new GitLabMergeEventWebhookRequestDto(
			"merge_request", "refs/heads/main", 1L, "test-user",
			List.of(commit1), // Single commit with mixed files
			new GitLabMergeEventWebhookRequestDto.Repository("repo-name", "repo-url", "desc", "homepage"),
			List.of(new GitLabMergeEventWebhookRequestDto.Label(1, "Label"))
		);
	}


}
