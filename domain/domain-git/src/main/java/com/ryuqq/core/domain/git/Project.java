package com.ryuqq.core.domain.git;

import java.util.Objects;

import com.ryuqq.core.storage.db.git.ProjectCommand;

public class Project {

	private Long id;
	private final Long gitProjectId;
	private final String name;
	private final String repositoryUrl;
	private final String owner;
	private final String description;

	public Project(Long gitProjectId, String name, String repositoryUrl, String owner, String description) {
		this.gitProjectId = gitProjectId;
		this.name = name;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public Project(Long id, Long gitProjectId, String name, String repositoryUrl, String owner, String description) {
		this.id = id;
		this.gitProjectId = gitProjectId;
		this.name = name;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public ProjectCommand toCommand() {
		return new ProjectCommand(id, gitProjectId, name, repositoryUrl, owner, description);
	}

	public Long getId() {
		return id;
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

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		Project project = (Project) object;
		return Objects.equals(id, project.id)
			&& Objects.equals(gitProjectId, project.gitProjectId)
			&& Objects.equals(name, project.name)
			&& Objects.equals(repositoryUrl, project.repositoryUrl)
			&& Objects.equals(owner, project.owner)
			&& Objects.equals(description, project.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, gitProjectId, name, repositoryUrl, owner, description);
	}

	@Override
	public String toString() {
		return "Project{"
			+
			"id="
			+ id
			+
			", gitProjectId="
			+ gitProjectId
			+
			", name='"
			+ name
			+ '\''
			+
			", repositoryUrl='"
			+ repositoryUrl
			+ '\''
			+
			", owner='"
			+ owner
			+ '\''
			+
			", description='"
			+ description
			+ '\''
			+
			'}';
	}
}
