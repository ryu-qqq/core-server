package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.PullRequest;
import com.ryuqq.core.storage.db.git.dto.PullRequestDto;

@Component
public class PullRequestMapper {

	public PullRequest toDomain(PullRequestDto pullRequestDto) {
		return PullRequest.create(
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
			pullRequestDto.getCreatedAt()
		);
	}
}
