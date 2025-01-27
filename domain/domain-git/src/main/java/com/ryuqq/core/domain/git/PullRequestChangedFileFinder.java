package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PullRequestChangedFileFinder {

	private final PullRequestChangedFileQueryRepository pullRequestChangedFileQueryRepository;

	public PullRequestChangedFileFinder(PullRequestChangedFileQueryRepository pullRequestChangedFileQueryRepository) {
		this.pullRequestChangedFileQueryRepository = pullRequestChangedFileQueryRepository;
	}

	public List<PullRequestChangedFileSummary> fetchById(long pullRequestId){
		return null;
		// return pullRequestChangedFileQueryRepository.fetchById(pullRequestId)
		// 	.stream()
		// 	.map(pullRequestChangedFileMapper::toDomain)
		// 	.toList();
	}



}
