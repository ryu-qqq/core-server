package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.response.PullRequestChangedFileResponseDto;
import com.ryuqq.core.domain.git.PullRequestChangedFileFinder;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PullRequestChangedFileFetchService {

	private final PullRequestChangedFileFinder pullRequestChangedFileFinder;

	public PullRequestChangedFileFetchService(PullRequestChangedFileFinder pullRequestChangedFileFinder) {
		this.pullRequestChangedFileFinder = pullRequestChangedFileFinder;
	}

	public List<PullRequestChangedFileResponseDto> fetchPullRequestChangedFilesById(long pullRequestId) {
		return pullRequestChangedFileFinder.fetchById(pullRequestId).stream()
			.map(p ->
				new PullRequestChangedFileResponseDto(
					p.repositoryName(),
					p.owner(),
					p.pullRequestId(),
					p.commitId(),
					p.changedFileId(),
					p.filePath(),
					p.className(),
					p.changeTyp(),
					p.reviewStatus()
			)).toList();
	}
}
