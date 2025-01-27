package com.ryuqq.core.api.controller.v1.git.service;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.git.response.PullRequestUpdatedResponseDto;
import com.ryuqq.core.domain.git.PullRequestUpdater;
import com.ryuqq.core.enums.ReviewStatus;


@Service
public class PullRequestCommandService {

	private final PullRequestUpdater pullRequestUpdater;

	public PullRequestCommandService(PullRequestUpdater pullRequestUpdater) {
		this.pullRequestUpdater = pullRequestUpdater;
	}

	public PullRequestUpdatedResponseDto updateReviewStatusById(long pullRequestId, ReviewStatus reviewStatus){
		pullRequestUpdater.updateReviewStatusById(pullRequestId, reviewStatus);
		return new PullRequestUpdatedResponseDto(pullRequestId);
	}

}
