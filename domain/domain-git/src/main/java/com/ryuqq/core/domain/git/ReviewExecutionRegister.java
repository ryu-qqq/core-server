package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

@Component
public class ReviewExecutionRegister {

	private final ReviewExecutionPersistenceRepository reviewExecutionPersistenceRepository;

	public ReviewExecutionRegister(ReviewExecutionPersistenceRepository reviewExecutionPersistenceRepository) {
		this.reviewExecutionPersistenceRepository = reviewExecutionPersistenceRepository;
	}

	public void register(ReviewExecution reviewExecution) {
		reviewExecutionPersistenceRepository.save(reviewExecution.toCommand());
	}

}
