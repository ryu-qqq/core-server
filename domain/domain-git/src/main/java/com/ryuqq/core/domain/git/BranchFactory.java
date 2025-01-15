package com.ryuqq.core.domain.git;

public class BranchFactory {

	public static Branch create(String branchName, String baseBranchName) {
		return new Branch(branchName, baseBranchName);
	}

	public static Branch create(long projectId, String branchName, String baseBranchName) {
		return new Branch(projectId, branchName, baseBranchName);
	}

	public static Branch create(long id, long projectId, String branchName, String baseBranchName) {
		return new Branch(id, projectId, branchName, baseBranchName);
	}

}
