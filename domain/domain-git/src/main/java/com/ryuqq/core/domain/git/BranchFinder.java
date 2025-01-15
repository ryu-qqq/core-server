package com.ryuqq.core.domain.git;

import com.ryuqq.core.domain.exception.DataNotFoundException;
import com.ryuqq.core.storage.db.git.BranchQueryRepository;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class BranchFinder {

	private final BranchMapper branchMapper;
	private final BranchQueryRepository branchQueryRepository;


	public BranchFinder(BranchMapper branchMapper, BranchQueryRepository branchQueryRepository) {
		this.branchMapper = branchMapper;
		this.branchQueryRepository = branchQueryRepository;
	}

	public Optional<Branch> fetchByProjectIdAndBranchName(long projectId, String branchName){
		return branchQueryRepository.fetchByProjectIdAndBranchName(projectId, branchName)
			.map(branchMapper::toDomain);
	}

	public Branch fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName){
		return branchQueryRepository.fetchByGitProjectIdAndBranchName(gitProjectId, branchName)
			.map(branchMapper::toDomain)
			.orElseThrow(() -> new DataNotFoundException(String.format("Branch not found %s, Branch Name %s", gitProjectId, branchName)));
	}


}
