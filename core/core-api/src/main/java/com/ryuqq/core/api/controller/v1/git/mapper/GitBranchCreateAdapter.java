package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.CreateBranchRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubCreateEventRequestDto;
import com.ryuqq.core.enums.GitType;

import org.springframework.stereotype.Component;

@Component
public class GitBranchCreateAdapter {

	public CreateBranchRequestDto toCreateBranchRequestDto(GitHubCreateEventRequestDto createEventRequestDto){
		return new CreateBranchRequestDto(
			createEventRequestDto.getProjectId(),
			GitType.GIT_HUB,
			createEventRequestDto.branchName(),
			createEventRequestDto.baseBranchName()
		);
	}

}
