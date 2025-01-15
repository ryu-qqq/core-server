package com.ryuqq.core.api.controller.v1.git.response;

import com.ryuqq.core.enums.GitType;

public record ProjectResponseDto(
	long projectId,
	Long gitProjectId,
	GitType gitType,
	String repositoryName,
	String repositoryUrl,
	String owner
) {
}
