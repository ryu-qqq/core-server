package com.ryuqq.core.api.controller.v1.git.request;

import com.ryuqq.core.enums.GitType;

public record CreateBranchRequestDto(
	long gitProjectId,
	GitType gitType,
	String branchName,
	String baseBranchName
) {
}
