package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.GitLabMergeEventWebhookRequestDto;
import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.ChangedFile;
import com.ryuqq.core.domain.git.Commit;
import com.ryuqq.core.domain.git.GitMergeRequestEvent;
import com.ryuqq.core.domain.git.Project;
import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class GitLabWebHookAdapter implements GitWebHookAdapter<GitLabMergeEventWebhookRequestDto> {

	@Override
	public GitMergeRequestEvent toMergeRequestEvent(GitLabMergeEventWebhookRequestDto requestDto) {
		Project project = toProjectDomain(requestDto);
		Branch branch = toBranchDomain(requestDto);
		List<Commit> commits = toCommitDomain(requestDto.commits());

		return new GitMergeRequestEvent(project, branch, commits);
	}

	private Project toProjectDomain(GitLabMergeEventWebhookRequestDto requestDto) {
		GitLabMergeEventWebhookRequestDto.Repository repo = requestDto.repository();
		return new Project(
			requestDto.projectId(),
			repo.name(),
			repo.url(),
			requestDto.userName(),
			repo.description()
		);
	}

	private Branch toBranchDomain(GitLabMergeEventWebhookRequestDto requestDto) {
		return new Branch(
			requestDto.repository().name(),
			requestDto.repository().url(),
			requestDto.ref()
		);
	}

	private List<Commit> toCommitDomain(List<GitLabMergeEventWebhookRequestDto.Commit> commits) {
		return commits.stream()
			.map(commit -> new Commit(
				commit.id(),
				commit.author().name(),
				commit.message(),
				commit.timestamp(),
				toChangedFileDomain(commit)
			))
			.toList();
	}

	private List<ChangedFile> toChangedFileDomain(GitLabMergeEventWebhookRequestDto.Commit commit) {
		return Stream.concat(
				commit.added().stream()
					.filter(this::isJavaFile)
					.map(file -> new ChangedFile(commit.id(), extractClassName(file), file, ChangeType.ADDED, TestStatus.PENDING)),
				commit.modified().stream()
					.filter(this::isJavaFile)
					.map(file -> new ChangedFile(commit.id(), extractClassName(file), file, ChangeType.MODIFIED, TestStatus.PENDING))
			)
			.distinct()
			.toList();
	}

	private static String extractClassName(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			return "";
		}
		return filePath.substring(filePath.lastIndexOf('/') + 1);
	}

	private boolean isJavaFile(String filePath) {
		return filePath != null && filePath.endsWith(".java");
	}


}
