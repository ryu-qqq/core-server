package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.storage.db.git.PullRequestPersistenceRepository;

import org.springframework.stereotype.Component;

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
