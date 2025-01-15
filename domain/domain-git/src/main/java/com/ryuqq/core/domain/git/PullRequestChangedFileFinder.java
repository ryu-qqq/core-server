package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.PullRequestChangedFileQueryRepository;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PullRequestChangedFileFinder {

	private final PullRequestChangedFileMapper pullRequestChangedFileMapper;
	private final PullRequestChangedFileQueryRepository pullRequestChangedFileQueryRepository;

	public PullRequestChangedFileFinder(PullRequestChangedFileMapper pullRequestChangedFileMapper,
										PullRequestChangedFileQueryRepository pullRequestChangedFileQueryRepository) {
		this.pullRequestChangedFileMapper = pullRequestChangedFileMapper;
		this.pullRequestChangedFileQueryRepository = pullRequestChangedFileQueryRepository;
	}

	public List<PullRequestChangedFileSummary> fetchById(long pullRequestId){
		return pullRequestChangedFileQueryRepository.fetchById(pullRequestId)
			.stream()
			.map(pullRequestChangedFileMapper::toDomain)
			.toList();
	}



}
