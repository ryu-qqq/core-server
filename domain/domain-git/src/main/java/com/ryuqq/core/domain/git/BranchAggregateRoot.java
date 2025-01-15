package com.ryuqq.core.domain.git;

import jakarta.transaction.Transactional;

import com.ryuqq.core.enums.GitType;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class BranchAggregateRoot {
	private final ProjectFinder projectFinder;
	private final BranchRegister branchRegister;
	private final BranchFinder branchFinder;

	public BranchAggregateRoot(ProjectFinder projectFinder, BranchRegister branchRegister, BranchFinder branchFinder) {
		this.projectFinder = projectFinder;
		this.branchRegister = branchRegister;
		this.branchFinder = branchFinder;
	}


	@Transactional
	public long fetchOrRegisterBranch(long gitProjectId, GitType gitType, Branch branch) {
		Project project = projectFinder.fetchByGitProjectIdAndGitType(gitProjectId, gitType);
		Optional<Branch> existingBranch = branchFinder.fetchByProjectIdAndBranchName(project.getId(), branch.getBranchName());
		if (existingBranch.isPresent()) {
			return existingBranch.get().getId();
		}
		return branchRegister.register(branch);
	}


}
