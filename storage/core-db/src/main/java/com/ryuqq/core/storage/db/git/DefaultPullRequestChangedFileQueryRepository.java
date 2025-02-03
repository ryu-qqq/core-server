package com.ryuqq.core.storage.db.git;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.PullRequestChangedFile;
import com.ryuqq.core.domain.git.PullRequestChangedFileQueryRepository;

@Repository
public class DefaultPullRequestChangedFileQueryRepository implements PullRequestChangedFileQueryRepository {

	private final PullRequestChangedFileDomainMapper pullRequestChangedFileDomainMapper;
	private final PullRequestChangedFileQueryDslRepository pullRequestChangedFileQueryDslRepository;

	public DefaultPullRequestChangedFileQueryRepository(
		PullRequestChangedFileDomainMapper pullRequestChangedFileDomainMapper,
		PullRequestChangedFileQueryDslRepository pullRequestChangedFileQueryDslRepository) {
		this.pullRequestChangedFileDomainMapper = pullRequestChangedFileDomainMapper;
		this.pullRequestChangedFileQueryDslRepository = pullRequestChangedFileQueryDslRepository;
	}

	@Override
	public List<PullRequestChangedFile> fetchById(long pullRequestId) {
		return pullRequestChangedFileQueryDslRepository.fetchById(pullRequestId)
			.stream()
			.map(pullRequestChangedFileDomainMapper::toDomain)
			.toList();
	}



}
