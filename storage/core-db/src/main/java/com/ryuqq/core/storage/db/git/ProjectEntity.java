package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity extends BaseEntity {

	@Column(name = "GIT_PROJECT_ID", nullable = false)
	private long gitProjectId;

	@Enumerated(EnumType.STRING)
	@Column(name = "GIT_TYPE", nullable = false)
	private GitType gitType;

	@Column(name = "REPOSITORY_NAME", nullable = false, length = 100)
	private String repositoryName;

	@Column(name = "REPOSITORY_URL", nullable = false, length = 255)
	private String repositoryUrl;

	@Column(name = "OWNER", nullable = false, length = 100)
	private String owner;

	@Column(name = "DESCRIPTION")
	private String description;

	protected ProjectEntity() {}

	public ProjectEntity(long gitProjectId, GitType gitType, String repositoryName, String repositoryUrl, String owner, String description) {
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public ProjectEntity(long id, long gitProjectId, GitType gitType, String repositoryName, String repositoryUrl, String owner, String description) {
		this.id = id;
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
		this.repositoryName = repositoryName;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public long getGitProjectId() {
		return gitProjectId;
	}

	public GitType getGitType() {
		return gitType;
	}

	public String getRepositoryName() {
		return repositoryName;
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
