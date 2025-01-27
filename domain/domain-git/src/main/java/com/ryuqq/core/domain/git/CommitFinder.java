package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class CommitFinder {

	private final CommitQueryRepository commitQueryRepository;

	public CommitFinder(CommitQueryRepository commitQueryRepository) {
		this.commitQueryRepository = commitQueryRepository;
	}

	public List<Commit> fetchByBranchIdAndFilePathIn(long branchId, List<String> filePaths){
		return commitQueryRepository.fetchByBranchIdAndFilePathIn(branchId, filePaths);
	}
}
