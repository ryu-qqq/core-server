package com.ryuqq.core.domain.git;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ProjectManager {

	private final ProjectFinder projectFinder;
	private final ProjectRegister projectRegister;

	public ProjectManager(ProjectFinder projectFinder, ProjectRegister projectRegister) {
		this.projectFinder = projectFinder;
		this.projectRegister = projectRegister;
	}

	public long findOrRegisterProject(Project project) {
		Optional<Project> projectOpt = projectFinder.findByGitProjectId(project.getGitProjectId());

		if(projectOpt.isPresent()) {
			Project findProject = projectOpt.get();
			return findProject.getId();
		}else{
			return projectRegister.register(project);
		}
	}



}
