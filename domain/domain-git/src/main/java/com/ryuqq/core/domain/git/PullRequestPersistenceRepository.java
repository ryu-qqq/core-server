package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ReviewStatus;

public interface PullRequestPersistenceRepository {


	long save(PullRequestCommand pullRequestCommand);
	void updateReviewStatus(long id, ReviewStatus reviewStatus);
}
