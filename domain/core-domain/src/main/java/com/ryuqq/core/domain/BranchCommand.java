package com.ryuqq.core.domain;

public record BranchCommand(
	long projectId,
	String repositoryName,
	String repositoryUrl,
	String name,
	String baseBranch
) {
}
