package com.ryuqq.core.domain;

import com.ryuqq.core.storage.db.git.BranchCommand;

import java.util.Objects;

public class Branch {
	private Long id;
	private long projectId;
	private String repositoryName;
	private String repositoryUrl;
	private String name;
	private String baseBranch;

	public Branch(long projectId, String repositoryName, String repositoryUrl, String name, String baseBranch) {
		this.projectId = projectId;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.name = name;
		this.baseBranch = baseBranch;
	}

	public Branch(Long id, long projectId, String repositoryName, String repositoryUrl, String name,
				  String baseBranch) {
		this.id = id;
		this.projectId = projectId;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.name = name;
		this.baseBranch = baseBranch;
	}

	public BranchCommand toCommand() {
		return new BranchCommand(id, projectId, repositoryName, repositoryUrl, name, baseBranch);
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

	public String getName() {
		return name;
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
			Objects.equals(this.name, that.name)
			&&
			Objects.equals(this.baseBranch, that.baseBranch);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, repositoryName, repositoryUrl, name, baseBranch);
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
			"name="
			+ name
			+ ", "
			+
			"baseBranch="
			+ baseBranch
			+ ']';
	}

}
