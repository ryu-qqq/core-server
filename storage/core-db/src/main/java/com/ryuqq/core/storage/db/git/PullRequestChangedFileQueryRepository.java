package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.PullRequestChangedFileDto;

import java.util.List;

public interface PullRequestChangedFileQueryRepository {

	List<PullRequestChangedFileDto> fetchById(long pullRequestId);

}
