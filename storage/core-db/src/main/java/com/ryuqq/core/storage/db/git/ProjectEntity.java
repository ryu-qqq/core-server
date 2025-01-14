package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity extends BaseEntity {

	@Column(name = "GITLAB_PROJECT_ID", nullable = false)
	private Long gitProjectId;

	@Column(name = "NAME", nullable = false, length = 255)
	private String name;

	@Column(name = "REPOSITORY_URL", nullable = false, length = 255)
	private String repositoryUrl;

	@Column(name = "OWNER", nullable = false, length = 255)
	private String owner;

	@Column(name = "DESCRIPTION")
	private String description;

	protected ProjectEntity() {}

	public ProjectEntity(Long gitProjectId, String name, String repositoryUrl, String owner, String description) {
		this.gitProjectId = gitProjectId;
		this.name = name;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public Long getGitProjectId() {
		return gitProjectId;
	}

	public String getName() {
		return name;
	}

	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public String getOwner() {
		return owner;
	}

	public String getDescription() {
		return description;
	}
}
