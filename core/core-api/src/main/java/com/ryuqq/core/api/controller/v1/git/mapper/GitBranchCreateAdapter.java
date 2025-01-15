package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.GitHubBranchCreateEventRequestDto;
import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.BranchFactory;

import org.springframework.stereotype.Component;

@Component
public class GitBranchCreateAdapter {

	public Branch toCreateBranchRequestDto(GitHubBranchCreateEventRequestDto createEventRequestDto){
		return BranchFactory.create(
			createEventRequestDto.branchName(),
			createEventRequestDto.baseBranchName()
		);
	}

}
