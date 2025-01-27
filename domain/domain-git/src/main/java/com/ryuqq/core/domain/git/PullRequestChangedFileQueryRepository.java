package com.ryuqq.core.domain.git;

import java.util.List;

public interface PullRequestChangedFileQueryRepository {

	List<PullRequestChangedFile> fetchById(long pullRequestId);

}
