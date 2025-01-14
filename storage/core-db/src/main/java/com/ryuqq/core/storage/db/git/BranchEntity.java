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

	@Column(name = "REPOSITORY_NAME", nullable = false, length = 255)
	private String repositoryName;

	@Column(name = "REPOSITORY_URL", nullable = false, length = 255)
	private String repositoryUrl;

	@Column(name = "BASE_BRANCH", nullable = false, length = 150)
	private String baseBranch;

	protected BranchEntity() {}

	public BranchEntity(long projectId, String repositoryName, String repositoryUrl, String baseBranch) {
		this.projectId = projectId;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.baseBranch = baseBranch;
	}

	public BranchEntity(long id, long projectId, String repositoryName, String repositoryUrl, String baseBranch) {
		this.id = id;
		this.projectId = projectId;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
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

	public String getBaseBranch() {
		return baseBranch;
	}
}
