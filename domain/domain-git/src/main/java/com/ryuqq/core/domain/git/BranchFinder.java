package com.ryuqq.core.domain.git;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class BranchFinder {

	private final BranchQueryRepository branchQueryRepository;

	public BranchFinder(BranchQueryRepository branchQueryRepository) {
		this.branchQueryRepository = branchQueryRepository;
	}

	public Optional<Branch> fetchByProjectIdAndBranchName(long projectId, String branchName){
		return branchQueryRepository.fetchByProjectIdAndBranchName(projectId, branchName);
	}

	public Branch fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName){
		return branchQueryRepository.fetchByGitProjectIdAndBranchName(gitProjectId, branchName);
	}


}
