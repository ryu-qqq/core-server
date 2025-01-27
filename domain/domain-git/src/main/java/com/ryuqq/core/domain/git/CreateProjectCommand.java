package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;

public record CreateProjectCommand(
	long gitProjectId,
	GitType gitType,
	String repositoryName,
	String repositoryUrl,
	String owner,
	String description
) implements ProjectCommand{

	@Override
	public Long id() {
		return null;
	}
}
