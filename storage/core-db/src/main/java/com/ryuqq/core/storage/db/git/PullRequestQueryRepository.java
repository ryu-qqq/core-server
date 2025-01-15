package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.PullRequestDto;
import com.ryuqq.core.storage.db.git.dto.PullRequestStorageFilterDto;

import java.util.List;

public interface PullRequestQueryRepository {

	List<PullRequestDto> fetchByFilter(PullRequestStorageFilterDto filterDto);
	long countByFilter(PullRequestStorageFilterDto filterDto);
}
