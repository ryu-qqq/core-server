package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.CommitQueryRepository;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommitFinder {

	private final CommitMapper commitMapper;
	private final CommitQueryRepository commitQueryRepository;

	public CommitFinder(CommitMapper commitMapper, CommitQueryRepository commitQueryRepository) {
		this.commitMapper = commitMapper;
		this.commitQueryRepository = commitQueryRepository;
	}


	public List<Commit> fetchByBranchIdAndFilePathIn(long branchId, List<String> filePaths){
		return commitQueryRepository.fetchByBranchIdAndFilePathIn(branchId, filePaths).stream()
			.map(commitMapper::toDomain)
			.toList();
	}
}
