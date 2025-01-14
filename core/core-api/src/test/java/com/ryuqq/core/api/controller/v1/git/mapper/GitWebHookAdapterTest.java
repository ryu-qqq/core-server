package com.ryuqq.core.api.controller.v1.git.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.ryuqq.core.api.controller.v1.git.request.GitLabMergeEventWebhookRequestDto;
import com.ryuqq.core.api.data.GitModuleHelper;
import com.ryuqq.core.domain.git.git.GitMergeRequestEvent;
import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class GitWebHookAdapterTest extends BaseUnitTest {

	@InjectMocks
	private GitWebHookAdapter gitWebHookAdapter;

	@Nested
	@DisplayName("toMergeRequestEvent 메서드")
	class ToMergeRequestEvent {

		@Test
		@DisplayName("GitMergeEventRequestDto를 GitMergeRequestEvent로 변환해야 한다")
		void shouldConvertGitMergeEventRequestDtoToGitMergeRequestEvent() {
			// Given
			GitLabMergeEventWebhookRequestDto requestDto = GitModuleHelper.toGitPushEventRequestDto();

			// When
			GitMergeRequestEvent event = gitWebHookAdapter.toMergeRequestEvent(requestDto);

			// Then
			assertAll("변환 결과 검증",
				() -> assertEquals("repo-name", event.project().getName(), "Project name이 변환되어야 함"),
				() -> assertEquals(2, event.commits().size(), "Commits 리스트가 올바르게 변환되어야 함"),

				// 첫 번째 커밋
				() -> assertEquals("1", event.commits().getFirst().getGitCommitId(), "첫 번째 Commit ID가 올바르게 변환되어야 함"),
				() -> assertEquals(1, event.commits().getFirst().getChangedFiles().size(),
					"첫 번째 Commit의 ChangedFiles가 올바르게 변환되어야 함"),
				() -> assertEquals("MyClass.java",
					event.commits().getFirst().getChangedFiles().getFirst().getClassName(),
					"첫 번째 Commit의 클래스명이 올바르게 변환되어야 함"),

				// 두 번째 커밋
				() -> assertEquals("2", event.commits().get(1).getGitCommitId(), "두 번째 Commit ID가 올바르게 변환되어야 함"),
				() -> assertEquals(1, event.commits().get(1).getChangedFiles().size(),
					"두 번째 Commit의 ChangedFiles가 올바르게 변환되어야 함"),
				() -> assertEquals("MyClass.java", event.commits().get(1).getChangedFiles().getFirst().getClassName(),
					"두 번째 Commit의 클래스명이 올바르게 변환되어야 함")
			);
		}

		@Test
		@DisplayName("Commits가 비어있는 경우 빈 GitMergeRequestEvent를 반환해야 한다")
		void shouldReturnEmptyGitMergeRequestEventWhenCommitsAreEmpty() {
			// Given
			GitLabMergeEventWebhookRequestDto requestDto = GitModuleHelper.toGitPushEventRequestDtoWithNoCommits();

			// When
			GitMergeRequestEvent event = gitWebHookAdapter.toMergeRequestEvent(requestDto);

			// Then
			assertAll("Commits가 비어있을 때 결과 검증",
				() -> assertEquals("repo-name", event.project().getName(), "Project name이 변환되어야 함"),
				() -> assertEquals(0, event.commits().size(), "Commits가 비어있어야 함")
			);
		}
	}


	@Nested
	@DisplayName("toChangedFileDomain 메서드")
	class ToChangedFileDomain {

		@Test
		@DisplayName("추가와 수정된 파일이 혼합된 경우 올바르게 처리해야 한다")
		void shouldHandleMixedAddedAndModifiedFiles() {
			// Given
			GitLabMergeEventWebhookRequestDto requestDto = GitModuleHelper.toGitPushEventRequestDtoWithMixedFiles();

			// When
			GitMergeRequestEvent event = gitWebHookAdapter.toMergeRequestEvent(requestDto);

			// Then
			assertEquals(1, event.commits().size(), "Commits가 1개여야 함");

			// 첫 번째 커밋의 파일 확인
			var firstCommitFiles = event.commits().getFirst().getChangedFiles();
			assertEquals(2, firstCommitFiles.size(), "ChangedFiles가 2개여야 함");

			// 첫 번째 파일은 추가된 파일
			assertEquals(ChangeType.ADDED, firstCommitFiles.get(0).getChangeType(), "첫 번째 파일은 추가된 파일이어야 함");
			assertEquals("MyClass.java", firstCommitFiles.get(0).getClassName(), "첫 번째 파일의 클래스명이 올바르게 변환되어야 함");

			// 두 번째 파일은 수정된 파일
			assertEquals(ChangeType.MODIFIED, firstCommitFiles.get(1).getChangeType(), "두 번째 파일은 수정된 파일이어야 함");
			assertEquals("YourClass.java", firstCommitFiles.get(1).getClassName(), "두 번째 파일의 클래스명이 올바르게 변환되어야 함");
		}

		@Test
		@DisplayName("파일 경로가 없는 경우 변경된 파일은 빈 컬렉션이어야 한다")
		void shouldReturnEmptyClassNameWhenFilePathIsEmpty() {
			// Given
			GitLabMergeEventWebhookRequestDto requestDto = GitModuleHelper.toGitPushEventRequestDtoWithEmptyFilePath();

			// When
			GitMergeRequestEvent event = gitWebHookAdapter.toMergeRequestEvent(requestDto);

			// Then
			var firstCommitFiles = event.commits().getFirst().getChangedFiles();
			assertTrue(firstCommitFiles.isEmpty(), "파일 경로가 없을 경우 클래스명은 빈 문자열이어야 함");
		}
	}


}
