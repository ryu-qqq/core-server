package com.ryuqq.core.api.controller.git.mapper;

import com.ryuqq.core.api.controller.git.request.GitPushEventRequestDto;
import com.ryuqq.core.domain.BranchCommand;
import com.ryuqq.core.domain.ChangedFileCommand;
import com.ryuqq.core.domain.GitEventCommand;
import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.CodeStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class GitWebHookAdapter {

	public GitEventCommand toCommand(GitPushEventRequestDto requestDto){
		return new GitEventCommand(
			new BranchCommand(
				requestDto.projectId(),
				requestDto.repository().name(),
				requestDto.repository().url(),
				requestDto.userName(),
				requestDto.ref()
			),
			toChangedFileCommands(requestDto.commits())
		);
	}

	private List<ChangedFileCommand> toChangedFileCommands(List<GitPushEventRequestDto.Commit> commits) {
		Map<String, GitPushEventRequestDto.Commit> latestCommitsByFile = commits.stream()
			.sorted(Comparator.comparing(GitPushEventRequestDto.Commit::timestamp).reversed()) // 최신순 정렬
			.flatMap(c -> Stream.concat(
				c.added().stream().map(file -> Map.entry(file, c)),
				c.modified().stream().map(file -> Map.entry(file, c))
			))
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				Map.Entry::getValue,
				(commit1, commit2) -> commit1
			));

		return latestCommitsByFile.entrySet().stream()
			.map(entry -> {
				String filePath = entry.getKey();
				GitPushEventRequestDto.Commit commit = entry.getValue();

				ChangeType changeType = commit.added().contains(filePath) ? ChangeType.ADDED : ChangeType.MODIFIED;

				return new ChangedFileCommand(
					extractClassName(filePath),
					filePath,
					changeType,
					CodeStatus.PENDING,
					commit.id(),
					commit.message()
				);
			})
			.toList();
	}


	private static String extractClassName(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			return "";
		}

		return filePath.substring(filePath.lastIndexOf('/') + 1);
	}

}
