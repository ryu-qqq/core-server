package com.ryuqq.core.storage.db.git.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ProjectDto {

	private long id;
	private long gitlabProjectId;
	private String name;
	private String repositoryUrl;
	private String owner;
	private String description;

	@QueryProjection
	public ProjectDto(long id, long gitlabProjectId, String name, String repositoryUrl, String owner,
					  String description) {
		this.id = id;
		this.gitlabProjectId = gitlabProjectId;
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
