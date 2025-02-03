package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;

public interface ProjectQueryRepository {
	Project fetchByGitProjectIdAndGitType(long gitProjectId, GitType gitType);
}
