package com.ryuqq.core.storage.db.git;

import java.io.Serializable;
import java.util.Objects;

public class BranchId implements Serializable {

	private long projectId;
	private String branchName;

	public BranchId() {}

	public BranchId(long projectId, String branchName) {
		this.projectId = projectId;
		this.branchName = branchName;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		BranchId branchId = (BranchId) object;
		return projectId
			== branchId.projectId
			&& Objects.equals(branchName, branchId.branchName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, branchName);
	}
}
