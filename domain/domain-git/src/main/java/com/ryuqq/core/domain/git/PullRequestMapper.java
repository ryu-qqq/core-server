package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.dto.PullRequestDto;

@Component
public class PullRequestMapper {

	public PullRequest toDomain(PullRequestDto pullRequestDto) {
		return new PullRequest(
			pullRequestDto.getId(),
			pullRequestDto.getGitType(),
			pullRequestDto.getGitPullId(),
			pullRequestDto.getBranchId(),
			pullRequestDto.getSourceBranch(),
			pullRequestDto.getTargetBranch(),
			pullRequestDto.getTitle(),
			pullRequestDto.getDescription(),
			pullRequestDto.getStatus(),
			pullRequestDto.getReviewStatus(),
			pullRequestDto.getCreateAt()
		);
	}
}
