package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class ReviewExecutionJpaPersistenceRepository implements ReviewExecutionPersistenceRepository {

	private final ReviewExecutionJpaRepository reviewExecutionJpaRepository;

	public ReviewExecutionJpaPersistenceRepository(ReviewExecutionJpaRepository reviewExecutionJpaRepository) {
		this.reviewExecutionJpaRepository = reviewExecutionJpaRepository;
	}

	@Override
	public void save(ReviewExecutionCommand reviewExecutionCommand) {
		reviewExecutionJpaRepository.save(reviewExecutionCommand.toEntity());
	}

}
