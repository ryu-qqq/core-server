package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class PullRequestJpaPersistenceRepository implements PullRequestPersistenceRepository{

	private final PullRequestJpaRepository pullRequestJpaRepository;

	public PullRequestJpaPersistenceRepository(PullRequestJpaRepository pullRequestJpaRepository) {
		this.pullRequestJpaRepository = pullRequestJpaRepository;
	}

	@Override
	public long save(PullRequestCommand pullRequestCommand) {
		return pullRequestJpaRepository.save(pullRequestCommand.toEntity()).getId();
	}

	@Override
	public void updateReviewStatus(long id, ReviewStatus reviewStatus) {
		pullRequestJpaRepository.findById(id)
			.ifPresentOrElse(pullRequest -> pullRequest.updateReviewStatus(reviewStatus),
				() -> {
					throw new  DataNotFoundException(String.format("Pull Request Not Found %s", id));
				}
			);
	}

}
