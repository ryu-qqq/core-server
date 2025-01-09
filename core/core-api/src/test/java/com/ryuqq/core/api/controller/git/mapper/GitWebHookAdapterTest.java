package com.ryuqq.core.api.controller.git.mapper;

import com.ryuqq.core.api.controller.git.request.GitPushEventRequestDto;
import com.ryuqq.core.domain.ChangedFileCommand;
import com.ryuqq.core.domain.GitEventCommand;
import com.ryuqq.core.unit.test.BaseUnitTest;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class GitWebHookAdapterTest extends BaseUnitTest {

	@InjectMocks
	private GitWebHookAdapter gitWebHookAdapter;

	@Nested
	@DisplayName("toCommand 메서드")
	class ToCommand {

		@Test
		@DisplayName("GitPushEventRequestDto를 GitEventCommand로 변환해야 한다")
		void shouldConvertGitPushEventRequestDtoToGitEventCommand() {
			// Given
			GitPushEventRequestDto requestDto = createTestRequestDto();

			// When
			GitEventCommand command = gitWebHookAdapter.toCommand(requestDto);

			// Then
			assertEquals("repo-name", command.branchCommand().repositoryName(), "Repository name이 변환되어야 함");
			assertEquals(1, command.changedFileCommands().size(), "중복 파일이 제거되어야 함");
			assertEquals("MyClass.java", command.changedFileCommands().get(0).className(), "클래스명이 올바르게 추출되어야 함");
		}
	}

	@Nested
	@DisplayName("toChangedFileCommands 메서드")
	class ToChangedFileCommands {

		@Test
		@DisplayName("중복된 파일 경로가 있을 때 가장 최근 Commit 정보로 처리해야 한다")
		void shouldHandleDuplicateFilePathsWithLatestCommit() {
			// Given
			GitPushEventRequestDto requestDto = createTestRequestDto();

			// When
			GitEventCommand gitEventCommand = gitWebHookAdapter.toCommand(requestDto);

			// Then
			assertEquals(1, gitEventCommand.changedFileCommands().size(), "중복된 파일 경로가 제거되어야 함");
			assertEquals("MyClass.java", gitEventCommand.changedFileCommands().get(0).className(), "클래스명이 올바르게 추출되어야 함");
			assertEquals("2", gitEventCommand.changedFileCommands().get(0).commitId(), "최신 Commit ID가 유지되어야 함");
		}
	}


	private GitPushEventRequestDto createTestRequestDto() {
		GitPushEventRequestDto.Commit commit1 = new GitPushEventRequestDto.Commit(
			"1", "First commit", LocalDateTime.parse("2025-01-08T10:00:00"),
			List.of("src/main/java/MyClass.java"), List.of(), List.of()
		);

		GitPushEventRequestDto.Commit commit2 = new GitPushEventRequestDto.Commit(
			"2", "Second commit", LocalDateTime.parse("2025-01-08T11:00:00"),
			List.of(), List.of("src/main/java/MyClass.java"), List.of()
		);

		return new GitPushEventRequestDto(
			"push", "refs/heads/main", 1L, "test-user",
			List.of(commit1, commit2),
			new GitPushEventRequestDto.Repository("repo-name", "repo-url", "desc", "homepage")
		);
	}



}
