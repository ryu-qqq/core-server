package com.ryuqq.core.storage.db.git.dto;

import com.ryuqq.core.enums.GitType;

import com.querydsl.core.annotations.QueryProjection;

public class ProjectDto {

	private long id;
	private long gitlabProjectId;
	private GitType gitType;
	private String name;
	private String repositoryUrl;
	private String owner;
	private String description;

	@QueryProjection
	public ProjectDto(long id, long gitlabProjectId, GitType gitType, String name, String repositoryUrl, String owner,
					  String description) {
		this.id = id;
		this.gitlabProjectId = gitlabProjectId;
		this.gitType = gitType;
		this.name = name;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public long getGitlabProjectId() {
		return gitlabProjectId;
	}

	public GitType getGitType() {
		return gitType;
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
