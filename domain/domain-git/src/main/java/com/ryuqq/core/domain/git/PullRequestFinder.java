package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class PullRequestFinder {

	// private final PullRequestMapper pullRequestMapper;
	// private final PullRequestQueryRepository pullRequestQueryRepository;
	//
	// public PullRequestFinder(PullRequestMapper pullRequestMapper, PullRequestQueryRepository pullRequestQueryRepository) {
	// 	this.pullRequestMapper = pullRequestMapper;
	// 	this.pullRequestQueryRepository = pullRequestQueryRepository;
	// }

	public List<PullRequest> fetchByFilter(PullRequestFilter filter){
		return null;
	}


	public long countByFilter(PullRequestFilter filter){
		return 0L;
	}


}
