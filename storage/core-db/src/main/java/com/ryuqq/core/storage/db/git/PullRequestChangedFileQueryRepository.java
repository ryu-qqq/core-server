package com.ryuqq.core.storage.db.git;

import java.util.List;

import com.ryuqq.core.storage.db.git.dto.PullRequestChangedFileDto;

public interface PullRequestChangedFileQueryRepository {

	List<PullRequestChangedFileDto> fetchById(long pullRequestId);

}
