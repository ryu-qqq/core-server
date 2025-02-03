package com.ryuqq.core.domain.git;

public record CreateBranchCommand(
	long projectId,
	String branchName,
	String baseBranchName
) implements BranchCommand {

	@Override
	public Long id() {
		return null;
	}
}
