package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

@Component
public class PullRequestChangedFileRegister {

	private final PullRequestChangedFilePersistenceRepository pullRequestChangedFilePersistenceRepository;

	public PullRequestChangedFileRegister(
		PullRequestChangedFilePersistenceRepository pullRequestChangedFilePersistenceRepository) {
		this.pullRequestChangedFilePersistenceRepository = pullRequestChangedFilePersistenceRepository;
	}

	public void register(PullRequestChangedFile pullRequestChangedFile){
		pullRequestChangedFilePersistenceRepository.save(pullRequestChangedFile.toCommand());
	}

}
