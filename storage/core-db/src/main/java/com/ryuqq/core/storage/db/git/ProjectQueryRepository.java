package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.ProjectDto;

import java.util.Optional;

public interface ProjectQueryRepository {

	Optional<ProjectDto> fetchByGitProjectId(long gitProjectId);
}
