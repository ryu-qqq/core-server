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
		Project project = toProjectDomain(requestDto);
		Branch branch = toBranchDomain(requestDto);
		List<Commit> commits = toCommitDomain(requestDto);

		return new GitMergeRequestEvent(project, branch, commits);
	}

	private Project toProjectDomain(GitHubMergeEventWebhookRequestDto requestDto) {
		return new Project(
			requestDto.repository().id(),
			requestDto.repository().name(),
			requestDto.repository().htmlUrl(),
			requestDto.sender().login(),
			null
		);
	}

	private Branch toBranchDomain(GitHubMergeEventWebhookRequestDto requestDto) {
		return new Branch(
			requestDto.repository().name(),
			requestDto.repository().htmlUrl(),
			requestDto.pullRequest().base().ref()
		);
	}

	private List<Commit> toCommitDomain(GitHubMergeEventWebhookRequestDto requestDto) {
		return gitHubFileAdapter.fetchChangedFiles(
			requestDto.repository(),
			requestDto.getCommitUrl(),
			requestDto.pullRequestNumber());
	}

}
