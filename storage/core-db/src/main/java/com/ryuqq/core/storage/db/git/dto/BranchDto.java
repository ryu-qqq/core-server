package com.ryuqq.core.storage.db.git.dto;

import com.querydsl.core.annotations.QueryProjection;

public class BranchDto {
	private long branchId;
	private long projectId;
	private String branchName;
	private String baseBranchName;

	@QueryProjection
	public BranchDto(long branchId, long projectId, String branchName, String baseBranchName) {
		this.branchId = branchId;
		this.projectId = projectId;
		this.branchName = branchName;
		this.baseBranchName = baseBranchName;
	}

	public long getBranchId() {
		return branchId;
	}

	public long getProjectId() {
		return projectId;
	}

	public String getBranchName() {
		return branchName;
	}

	public String getBaseBranchName() {
		return baseBranchName;
	}
}
