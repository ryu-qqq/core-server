package com.ryuqq.core.api.controller.v1.git.response;

public record ProjectResponseDto(
	long projectId,
	Long gitProjectId,
	String repositoryUrl,
	String owner
) {
}
