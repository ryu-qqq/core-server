package com.ryuqq.core.storage.db.git;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.Commit;
import com.ryuqq.core.domain.git.CommitQueryRepository;


@Repository
public class DefaultCommitQueryRepository implements CommitQueryRepository {

	private final CommitDomainMapper commitDomainMapper;
	private final CommitQueryDslRepository commitQueryDslRepository;

	public DefaultCommitQueryRepository(CommitDomainMapper commitDomainMapper, CommitQueryDslRepository commitQueryDslRepository) {
		this.commitDomainMapper = commitDomainMapper;
		this.commitQueryDslRepository = commitQueryDslRepository;
	}

	@Override
	public List<Commit> fetchByBranchIdAndFilePathIn(long branchId, List<String> filePaths) {
		return commitQueryDslRepository.fetchByBranchIdAndFilePathIn(branchId, filePaths)
			.stream()
			.map(commitDomainMapper::toDomain)
			.toList();
	}
}
