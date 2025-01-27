package com.ryuqq.core.domain.git;

public record UpdateBranchCommand(
	Long id,
	long projectId,
	String branchName,
	String baseBranchName
) implements BranchCommand{

}
