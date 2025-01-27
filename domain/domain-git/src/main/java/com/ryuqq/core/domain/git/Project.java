package com.ryuqq.core.domain.git;

import java.util.Objects;

import com.ryuqq.core.enums.GitType;

public class Project {

	private Long id;
	private final Long gitProjectId;
	private final GitType gitType;
	private final String repositoryName;
	private final String repositoryUrl;
	private final String owner;
	private final String description;

	private Project(Long id, Long gitProjectId, GitType gitType, String name, String repositoryUrl, String owner, String description) {
		this.id = id;
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
		this.repositoryName = name;
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}


	public static Project create(Long id, Long gitProjectId, GitType gitType, String name, String repositoryUrl, String owner, String description) {
		return new Project(id, gitProjectId, gitType, name, repositoryUrl, owner, description);
	}


	public ProjectCommand toCommand() {
		if(id != null){
			return new UpdateProjectCommand(id, gitProjectId, gitType, repositoryName, repositoryUrl, owner, description);
		}
		return new CreateProjectCommand(gitProjectId, gitType, repositoryName, repositoryUrl, owner, description);
	}

	public Long getId() {
		return id;
	}

	public Long getGitProjectId() {
		return gitProjectId;
	}

	public GitType getGitType() {
		return gitType;
	}

	public String getName() {
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
			&& Objects.equals(repositoryName, project.repositoryName)
			&& Objects.equals(repositoryUrl, project.repositoryUrl)
			&& Objects.equals(owner, project.owner)
			&& Objects.equals(description, project.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, gitProjectId, repositoryName, repositoryUrl, owner, description);
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
			", gitType="
			+ gitType
			+
			", name='"
			+ repositoryName
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
