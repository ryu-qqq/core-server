package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.request.GitHubMergeEventWebhookRequestDto;
import com.ryuqq.core.domain.git.ChangedFile;
import com.ryuqq.core.domain.git.Commit;
import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.external.git.GitHubDataFetcher;
import com.ryuqq.core.external.git.response.GitHubCommitResponse;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GitHubFileAdapter {

	private final GitHubDataFetcher gitHubDataFetcher;

	public GitHubFileAdapter(GitHubDataFetcher gitHubDataFetcher) {
		this.gitHubDataFetcher = gitHubDataFetcher;
	}

	public List<Commit> fetchChangedFiles(String author, String fullName, int pullNumber) {
		List<ChangedFile> changedFiles = gitHubDataFetcher.fetchCommitInfo(fullName, pullNumber).stream()
			.filter(g -> isJavaFile(g.filename()))
			.map(g -> new ChangedFile(
					g.sha(),
					extractClassName(g.filename()),
					g.filename(),
					ChangeType.of(g.status().toUpperCase()),
					TestStatus.PENDING
				)).toList();

		Commit commit = new Commit(
			"",
			author,
			"",
			OffsetDateTime.now(),
			changedFiles
		);

		return List.of(commit);
	}

	private static String extractClassName(String filePath) {
		int lastSlashIndex = filePath.lastIndexOf("/");
		return (lastSlashIndex != -1) ? filePath.substring(lastSlashIndex + 1) : filePath;
	}

	private boolean isJavaFile(String filePath) {
		return filePath != null && filePath.endsWith(".java");
	}


}
