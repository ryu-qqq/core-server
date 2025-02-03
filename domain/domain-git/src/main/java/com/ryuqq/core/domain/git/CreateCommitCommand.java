package com.ryuqq.core.domain.git;

import java.time.LocalDateTime;

public record CreateCommitCommand(
	Long commitId,
	String gitCommitId,
	String author,
	String commitMessage,
	LocalDateTime timestamp
) implements CommitCommand{
	@Override
	public Long id() {
		return null;
	}
}
