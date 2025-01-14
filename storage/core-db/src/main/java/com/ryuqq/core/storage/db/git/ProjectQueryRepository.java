package com.ryuqq.core.storage.db.git;

import java.util.Optional;

import com.ryuqq.core.storage.db.git.dto.ProjectDto;

public interface ProjectQueryRepository {

	Optional<ProjectDto> fetchByGitProjectId(long gitProjectId);
}
