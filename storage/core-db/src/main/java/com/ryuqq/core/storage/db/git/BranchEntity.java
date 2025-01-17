package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "BRANCH")
@Entity
public class BranchEntity extends BaseEntity {

	@Column(name = "PROJECT_ID", nullable = false)
	private long projectId;

	@Column(name = "BRANCH_NAME", nullable = false, length = 100)
	private String branchName;

	@Column(name = "BASE_BRANCH_NAME", nullable = false, length = 100)
	private String baseBranchName;

	protected BranchEntity() {}

	public BranchEntity(long projectId, String branchName, String baseBranchName) {
		this.projectId = projectId;
		this.branchName = branchName;
		this.baseBranchName = baseBranchName;
	}

	public BranchEntity(long id, long projectId, String branchName, String baseBranchName) {
		this.projectId = projectId;
		this.branchName = branchName;
		this.baseBranchName = baseBranchName;
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
