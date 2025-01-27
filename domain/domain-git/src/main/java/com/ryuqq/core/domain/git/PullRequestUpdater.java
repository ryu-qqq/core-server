package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ReviewStatus;

@Component
public class PullRequestUpdater {

	private final PullRequestPersistenceRepository pullRequestPersistenceRepository;

	public PullRequestUpdater(PullRequestPersistenceRepository pullRequestPersistenceRepository) {
		this.pullRequestPersistenceRepository = pullRequestPersistenceRepository;
	}

	public void updateReviewStatusById(long pullRequestId, ReviewStatus reviewStatus){
		pullRequestPersistenceRepository.updateReviewStatus(pullRequestId, reviewStatus);
	}

}
