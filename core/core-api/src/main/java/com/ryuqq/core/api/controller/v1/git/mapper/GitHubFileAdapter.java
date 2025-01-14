package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.GitHubMergeEventWebhookRequestDto;
import com.ryuqq.core.domain.git.ChangedFile;
import com.ryuqq.core.domain.git.Commit;
import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.external.git.GitHubDataFetcher;
import com.ryuqq.core.external.git.response.GitHubCommitResponse;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GitHubFileAdapter {

	private final GitHubDataFetcher gitHubDataFetcher;

	public GitHubFileAdapter(GitHubDataFetcher gitHubDataFetcher) {
		this.gitHubDataFetcher = gitHubDataFetcher;
	}


	public List<Commit> fetchChangedFiles(GitHubMergeEventWebhookRequestDto.Repository repository, String commitUrl, int pullNumber) {
		String owner = repository.getOwnerLogin();
		String repo = repository.name();

		Commit commit = mapToCommit(gitHubDataFetcher.fetchCommitInfo(commitUrl));

		gitHubDataFetcher.fetchChangedFiles(owner, repo, pullNumber)
			.forEach(fileResponse -> {
				commit.addChangedFile(
					new ChangedFile(
						commit.getGitCommitId(),
						extractClassName(fileResponse.filename()),
						fileResponse.filename(),
						ChangeType.ADDED,
						TestStatus.PENDING
					)
				);
			});
		return List.of(commit);
	}

	private Commit mapToCommit(GitHubCommitResponse response) {
		return new Commit(
			response.sha(),
			response.getAuthor(),
			response.getMessage(),
			response.getOffsetTimeDate(),
			List.of()
		);

	}

	private static String extractClassName(String filePath) {
		int lastSlashIndex = filePath.lastIndexOf("/");
		return (lastSlashIndex != -1) ? filePath.substring(lastSlashIndex + 1) : filePath;
	}

}
