package com.ryuqq.core.domain.git;

import java.util.Objects;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.storage.db.git.ProjectCommand;

public class Project {

	private Long id;
	private final Long gitProjectId;
	private final GitType gitType;
<<<<<<< HEAD
	private final String repositoryName;
=======
	private final String name;
>>>>>>> main
	private final String repositoryUrl;
	private final String owner;
	private final String description;

	public Project(Long gitProjectId, GitType gitType, String name, String repositoryUrl, String owner, String description) {
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
<<<<<<< HEAD
		this.repositoryName = name;
=======
		this.name = name;
>>>>>>> main
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public Project(Long id, Long gitProjectId, GitType gitType, String name, String repositoryUrl, String owner, String description) {
		this.id = id;
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
<<<<<<< HEAD
		this.repositoryName = name;
=======
		this.name = name;
>>>>>>> main
		this.repositoryUrl = repositoryUrl;
		this.owner = owner;
		this.description = description;
	}

	public ProjectCommand toCommand() {
<<<<<<< HEAD
		return new ProjectCommand(id, gitProjectId, gitType, repositoryName, repositoryUrl, owner, description);
=======
		return new ProjectCommand(id, gitProjectId, gitType, name, repositoryUrl, owner, description);
>>>>>>> main
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
<<<<<<< HEAD
			&& Objects.equals(repositoryName, project.repositoryName)
=======
			&& gitType
			== project.gitType
			&& Objects.equals(name, project.name)
>>>>>>> main
			&& Objects.equals(repositoryUrl, project.repositoryUrl)
			&& Objects.equals(owner, project.owner)
			&& Objects.equals(description, project.description);
	}

	@Override
	public int hashCode() {
<<<<<<< HEAD
		return Objects.hash(id, gitProjectId, repositoryName, repositoryUrl, owner, description);
=======
		return Objects.hash(id, gitProjectId, gitType, name, repositoryUrl, owner, description);
>>>>>>> main
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
