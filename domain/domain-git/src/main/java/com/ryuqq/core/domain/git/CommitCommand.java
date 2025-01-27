package com.ryuqq.core.domain.git;

import java.time.LocalDateTime;

public interface CommitCommand {
	Long id();
	Long commitId();
	String gitCommitId();
	String author();
	String commitMessage();
	LocalDateTime timestamp();

}
