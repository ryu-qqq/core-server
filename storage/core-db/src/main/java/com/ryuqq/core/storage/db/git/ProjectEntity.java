package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
<<<<<<< HEAD
import jakarta.persistence.IdClass;
=======
>>>>>>> main
import jakarta.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity extends BaseEntity {

	@Column(name = "GIT_PROJECT_ID", nullable = false)
<<<<<<< HEAD
	private long gitProjectId;

	@Enumerated(EnumType.STRING)
	@Column(name = "GIT_TYPE", nullable = false)
	private GitType gitType;

	@Column(name = "REPOSITORY_NAME", nullable = false, length = 100)
	private String repositoryName;
=======
	private Long gitProjectId;

	@Enumerated(EnumType.STRING)
	@Column(name = "GIT_TYPE")
	private GitType gitType;

	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
>>>>>>> main

	@Column(name = "REPOSITORY_URL", nullable = false, length = 255)
	private String repositoryUrl;

	@Column(name = "OWNER", nullable = false, length = 100)
	private String owner;

	@Column(name = "DESCRIPTION")
	private String description;

	protected ProjectEntity() {}

<<<<<<< HEAD
	public ProjectEntity(long gitProjectId, GitType gitType, String repositoryName, String repositoryUrl, String owner, String description) {
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
		this.repositoryName = repositoryName;
=======
	public ProjectEntity(Long gitProjectId, GitType gitType, String name, String repositoryUrl, String owner, String description) {
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
		this.name = name;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public ProjectEntity(long id, Long gitProjectId, GitType gitType, String name, String repositoryUrl, String owner, String description) {
		this.id = id;
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
		this.name = name;
>>>>>>> main
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

<<<<<<< HEAD
	public String getRepositoryName() {
		return repositoryName;
=======
	public String getName() {
		return name;
>>>>>>> main
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
