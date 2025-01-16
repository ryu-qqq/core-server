package com.ryuqq.core.api.controller.v1.git.service;

import jakarta.transaction.Transactional;

import com.ryuqq.core.api.controller.v1.git.response.PullRequestUpdatedResponseDto;
import com.ryuqq.core.domain.git.PullRequestUpdater;
import com.ryuqq.core.enums.ReviewStatus;

import org.springframework.stereotype.Service;

@Service
public class PullRequestCommandService {

	private final PullRequestUpdater pullRequestUpdater;

	public PullRequestCommandService(PullRequestUpdater pullRequestUpdater) {
		this.pullRequestUpdater = pullRequestUpdater;
	}

	@Transactional
	public PullRequestUpdatedResponseDto updateReviewStatusById(long pullRequestId, ReviewStatus reviewStatus){
		pullRequestUpdater.updateReviewStatusById(pullRequestId, reviewStatus);
		return new PullRequestUpdatedResponseDto(pullRequestId);
	}

}
