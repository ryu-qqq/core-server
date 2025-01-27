package com.ryuqq.core.domain.git;

public interface BranchCommand {
	Long id();
	long projectId();
	String branchName();
	String baseBranchName();

}
