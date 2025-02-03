package com.ryuqq.core.domain.git;

import java.util.Objects;


public class Branch {
	private Long id;
	private long projectId;
	private final String branchName;
	private final String baseBranchName;

	protected Branch(String branchName, String baseBranchName) {
		this.branchName = branchName;
		this.baseBranchName = baseBranchName;
	}

	protected Branch(long projectId, String branchName, String baseBranchName) {
		this.projectId = projectId;
		this.branchName = branchName;
		this.baseBranchName = baseBranchName;
	}

	protected Branch(Long id, long projectId, String branchName, String baseBranchName) {
		this.id = id;
		this.projectId = projectId;
		this.branchName = branchName;
		this.baseBranchName = baseBranchName;
	}

	public BranchCommand toCommand() {
		if(id != null){
			return new UpdateBranchCommand(id, projectId, branchName, baseBranchName);
		}
		return new CreateBranchCommand(projectId, branchName, baseBranchName);
	}

	public Long getId() {
		return id;
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

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		Branch branch = (Branch) object;
		return projectId
			== branch.projectId
			&& Objects.equals(id, branch.id)
			&& Objects.equals(branchName, branch.branchName)
			&& Objects.equals(baseBranchName, branch.baseBranchName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, projectId, branchName, baseBranchName);
	}

	@Override
	public String toString() {
		return "Branch{"
			+
			"id="
			+ id
			+
			", projectId="
			+ projectId
			+
			", branchName='"
			+ branchName
			+ '\''
			+
			", baseBranchName='"
			+ baseBranchName
			+ '\''
			+
			'}';
	}
}
