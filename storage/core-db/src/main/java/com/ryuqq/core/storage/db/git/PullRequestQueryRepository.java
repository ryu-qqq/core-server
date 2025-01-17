package com.ryuqq.core.storage.db.git;

import java.util.List;

import com.ryuqq.core.storage.db.git.dto.PullRequestDto;
import com.ryuqq.core.storage.db.git.dto.PullRequestStorageFilterDto;

public interface PullRequestQueryRepository {

	List<PullRequestDto> fetchByFilter(PullRequestStorageFilterDto filterDto);
	long countByFilter(PullRequestStorageFilterDto filterDto);
}
