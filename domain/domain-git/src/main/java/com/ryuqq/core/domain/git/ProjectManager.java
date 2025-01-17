package com.ryuqq.core.domain.git;


import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.GitType;

@Component
public class ProjectManager {

	private final ProjectFinder projectFinder;
	private final ProjectRegister projectRegister;

	public ProjectManager(ProjectFinder projectFinder, ProjectRegister projectRegister) {
		this.projectFinder = projectFinder;
		this.projectRegister = projectRegister;
	}

	public long findOrRegisterProject(Project project) {
		return projectFinder.fetchByGitProjectIdAndGitType(project.getGitProjectId(), GitType.GIT_HUB).getId();

	}



}
