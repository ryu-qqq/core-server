package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.GitType;

public interface ProjectCommand {

	Long id();
	long gitProjectId();
	GitType gitType();
	String repositoryName();
	String repositoryUrl();
	String owner();
	String description();

}
