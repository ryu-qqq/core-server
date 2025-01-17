package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.PullRequestQueryRepository;

@Component
public class PullRequestFinder {

	private final PullRequestMapper pullRequestMapper;
	private final PullRequestQueryRepository pullRequestQueryRepository;

	public PullRequestFinder(PullRequestMapper pullRequestMapper, PullRequestQueryRepository pullRequestQueryRepository) {
		this.pullRequestMapper = pullRequestMapper;
		this.pullRequestQueryRepository = pullRequestQueryRepository;
	}

	public List<PullRequest> fetchByFilter(PullRequestFilter filter){
		return pullRequestQueryRepository.fetchByFilter(filter.toStorageFilterDto()).stream()
			.map(pullRequestMapper::toDomain)
			.toList();
	}


	public long countByFilter(PullRequestFilter filter){
		return pullRequestQueryRepository.countByFilter(filter.toStorageFilterDto());
	}


}
