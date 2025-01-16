package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ReviewStatus;

public interface PullRequestPersistenceRepository {


	long save(PullRequestCommand pullRequestCommand);
	void updateReviewStatus(long id, ReviewStatus reviewStatus);
}
