package com.ryuqq.core.domain.git;

public class BranchFactory {

	public static Branch create(long projectId, String branchName, String baseBranchName) {
		return new Branch(projectId, branchName, baseBranchName);
	}
}
