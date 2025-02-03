package com.ryuqq.core.storage.db.git;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.exception.DataNotFoundException;
import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.BranchQueryRepository;

@Repository
public class DefaultBranchQueryRepository implements BranchQueryRepository {

	private final BranchDomainMapper branchDomainMapper;
	private final BranchQueryDslRepository branchQueryDslRepository;

	public DefaultBranchQueryRepository(BranchDomainMapper branchDomainMapper, BranchQueryDslRepository branchQueryDslRepository) {
		this.branchDomainMapper = branchDomainMapper;
		this.branchQueryDslRepository = branchQueryDslRepository;
	}

	@Override
	public Optional<Branch> fetchByProjectIdAndBranchName(long projectId, String branchName) {
		return branchQueryDslRepository.fetchByProjectIdAndBranchName(projectId, branchName)
			.map(branchDomainMapper::toDomain);
	}

	@Override
	public Branch fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName) {
		return branchQueryDslRepository.fetchByGitProjectIdAndBranchName(gitProjectId, branchName)
			.map(branchDomainMapper::toDomain)
			.orElseThrow(() -> new DataNotFoundException(String.format("Branch not found %s, Branch Name %s", gitProjectId, branchName)));
	}



}
