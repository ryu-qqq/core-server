package com.ryuqq.core.domain.git;

import java.util.Objects;

import com.ryuqq.core.storage.db.git.BranchCommand;

public class Branch {
	private Long id;
	private long projectId;
	private String repositoryName;
	private String repositoryUrl;
	private String baseBranch;

	public Branch(String repositoryName, String repositoryUrl, String baseBranch) {
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.baseBranch = baseBranch;
	}

	public Branch(Long id, long projectId, String repositoryName, String repositoryUrl, String baseBranch) {
		this.id = id;
		this.projectId = projectId;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.baseBranch = baseBranch;
	}

	public BranchCommand toCommand(long projectId) {
		return new BranchCommand(null, projectId, repositoryName, repositoryUrl, baseBranch);
	}

	public Long getId() {
		return id;
	}

	public long getProjectId() {
		return projectId;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public String getRepositoryUrl() {
		return repositoryUrl;
	}


	public String getBaseBranch() {
		return baseBranch;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj
			== this) return true;
		if (obj
			== null
			|| obj.getClass()
			!= this.getClass()) return false;
		var that = (Branch) obj;
		return this.projectId
			== that.projectId
			&&
			Objects.equals(this.repositoryName, that.repositoryName)
			&&
			Objects.equals(this.repositoryUrl, that.repositoryUrl)
			&&
			Objects.equals(this.baseBranch, that.baseBranch);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, repositoryName, repositoryUrl, baseBranch);
	}

	@Override
	public String toString() {
		return "Branch["
			+
			"projectId="
			+ projectId
			+ ", "
			+
			"repositoryName="
			+ repositoryName
			+ ", "
			+
			"repositoryUrl="
			+ repositoryUrl
			+ ", "
			+
			"baseBranch="
			+ baseBranch
			+ ']';
	}

}
