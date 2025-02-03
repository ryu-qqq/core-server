package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.PullRequestCommand;
import com.ryuqq.core.domain.git.PullRequestPersistenceRepository;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class PullRequestJpaPersistenceRepository implements PullRequestPersistenceRepository {

	private final PullRequestDomainMapper pullRequestDomainMapper;
	private final PullRequestJpaRepository pullRequestJpaRepository;

	public PullRequestJpaPersistenceRepository(PullRequestDomainMapper pullRequestDomainMapper, PullRequestJpaRepository pullRequestJpaRepository) {
		this.pullRequestDomainMapper = pullRequestDomainMapper;
		this.pullRequestJpaRepository = pullRequestJpaRepository;
	}

	@Override
	public long save(PullRequestCommand pullRequestCommand) {
		return pullRequestJpaRepository.save(pullRequestDomainMapper.toEntity(pullRequestCommand)).getId();
	}

	@Override
	public void updateReviewStatus(long id, ReviewStatus reviewStatus) {
		pullRequestJpaRepository.findById(id)
			.ifPresentOrElse(pullRequest -> pullRequest.updateReviewStatus(reviewStatus),
				() -> {
					throw new DataNotFoundException(String.format("Pull Request Not Found %s", id));
				}
			);
	}

}
