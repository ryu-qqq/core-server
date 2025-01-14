package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class GitMergeRequestManager {

	private final ProjectManager projectManager;
	private final BranchManager branchManager;
	private final MergeReportRegister mergeReportRegister;

	public GitMergeRequestManager(ProjectManager projectManager, BranchManager branchManager,
								  MergeReportRegister mergeReportRegister) {
		this.projectManager = projectManager;
		this.branchManager = branchManager;
		this.mergeReportRegister = mergeReportRegister;
	}

	@Transactional
	public long register(GitMergeRequestEvent gitMergeRequestEvent) {
		long projectId = projectManager.findOrRegisterProject(gitMergeRequestEvent.project());
		long branchId = branchManager.registerBranchWithCommits(projectId, gitMergeRequestEvent.branch(), gitMergeRequestEvent.commits());

		mergeReportRegister.register(MergeReport.init(branchId, gitMergeRequestEvent.getTotalFilesCount()));

		return projectId;
	}

}
