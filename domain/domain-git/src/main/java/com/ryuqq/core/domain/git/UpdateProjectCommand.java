package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;

public record UpdateProjectCommand(
	Long id,
	long gitProjectId,
	GitType gitType,
	String repositoryName,
	String repositoryUrl,
	String owner,
	String description
) implements ProjectCommand {
}
