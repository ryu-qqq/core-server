package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.GitHubMergeEventWebhookRequestDto;
import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.Commit;
import com.ryuqq.core.domain.git.GitMergeRequestEvent;
import com.ryuqq.core.domain.git.Project;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class GitHubWebHookAdapter implements GitWebHookAdapter<GitHubMergeEventWebhookRequestDto> {

	private final GitHubFileAdapter gitHubFileAdapter;

	public GitHubWebHookAdapter(GitHubFileAdapter gitHubFileAdapter) {
		this.gitHubFileAdapter = gitHubFileAdapter;
	}

	@Override
	public GitMergeRequestEvent toMergeRequestEvent(GitHubMergeEventWebhookRequestDto requestDto) {
		Project project = toProjectDomain(requestDto.repository(), requestDto.sender());
		Branch branch = toBranchDomain(requestDto.repository(), requestDto.getBranchName());
		List<Commit> commits = toCommitDomain(requestDto);

		return new GitMergeRequestEvent(project, branch, commits);
	}

	private Project toProjectDomain(GitHubMergeEventWebhookRequestDto.Repository repository, GitHubMergeEventWebhookRequestDto.Sender sender) {
		return new Project(
			repository.id(),
			repository.name(),
			repository.htmlUrl(),
			sender.login(),
			null
		);
	}

	private Branch toBranchDomain(GitHubMergeEventWebhookRequestDto.Repository repository, String branchName) {
		return new Branch(
			repository.name(),
			repository.htmlUrl(),
			branchName
		);
	}

	private List<Commit> toCommitDomain(GitHubMergeEventWebhookRequestDto requestDto) {
		return gitHubFileAdapter.fetchChangedFiles(
			requestDto.getOwner(),
			requestDto.getFullName(),
			requestDto.pullRequestNumber());
	}



}
