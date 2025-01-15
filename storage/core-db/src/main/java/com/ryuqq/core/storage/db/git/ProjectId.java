package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;

import java.io.Serializable;
import java.util.Objects;

public class ProjectId implements Serializable {

	private long gitProjectId;
	private GitType gitType;

	public ProjectId(){}

	public ProjectId(long gitProjectId, GitType gitType) {
		this.gitProjectId = gitProjectId;
		this.gitType = gitType;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ProjectId projectId = (ProjectId) object;
		return gitProjectId
			== projectId.gitProjectId
			&& gitType
			== projectId.gitType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gitProjectId, gitType);
	}
}
