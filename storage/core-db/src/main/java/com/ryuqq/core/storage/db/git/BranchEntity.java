package com.ryuqq.core.storage.db.git;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.ryuqq.core.storage.db.BaseEntity;

@Table(name = "BRANCH")
@Entity
public class BranchEntity extends BaseEntity {

	@Column(name = "PROJECT_ID", nullable = false)
	private long projectId;

	@Column(name = "REPOSITORY_NAME", nullable = false, length = 255)
	private String repositoryName;

	@Column(name = "REPOSITORY_URL", nullable = false, length = 255)
	private String repositoryUrl;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "BASE_BRANCH", nullable = false, length = 150)
	private String baseBranch;

	protected BranchEntity() {}

	public BranchEntity(long projectId, String repositoryName, String repositoryUrl, String name, String baseBranch) {
		this.projectId = projectId;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.name = name;
		this.baseBranch = baseBranch;
	}

	public BranchEntity(long id, long projectId, String repositoryName, String repositoryUrl, String name, String baseBranch) {
		this.id = id;
		this.projectId = projectId;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.name = name;
		this.baseBranch = baseBranch;
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
}
