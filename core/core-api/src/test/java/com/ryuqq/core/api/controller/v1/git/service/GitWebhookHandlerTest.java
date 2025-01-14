package com.ryuqq.core.api.controller.v1.git.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.api.controller.v1.git.mapper.GitWebHookAdapter;
import com.ryuqq.core.api.controller.v1.git.request.GitMergeEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidationResult;
import com.ryuqq.core.api.controller.v1.git.validator.LabelValidator;
import com.ryuqq.core.domain.git.GitMergeRequestManager;
import com.ryuqq.core.domain.git.GitMergeRequestEvent;
import com.ryuqq.core.unit.test.BaseUnitTest;

import java.util.List;

class GitWebhookHandlerTest extends BaseUnitTest {

	@InjectMocks
	private GitWebhookHandler gitWebhookHandler;

	@Mock
	GitMergeRequestManager gitMergeRequestManager;

	@Mock
	GitWebHookAdapter gitWebHookAdapter;

	@Mock
	LabelValidator labelValidator;


	@DisplayName("깃 웹훅을 정상적으로 핸들링한다.")
	@Test
	void shouldHandleWhenGitWebHookTrigger() {
		// Given
		long expectedId = 1L;
		GitMergeEventRequestDto gitMergeEventRequestDto = mock(GitMergeEventRequestDto.class);
		GitMergeRequestEvent gitMergeRequestEvent = mock(GitMergeRequestEvent.class);

		when(labelValidator.validate(gitMergeEventRequestDto.labels()))
			.thenReturn(new LabelValidationResult(List.of()));
		when(gitWebHookAdapter.toMergeRequestEvent(gitMergeEventRequestDto))
			.thenReturn(gitMergeRequestEvent);
		when(gitMergeRequestManager.register(gitMergeRequestEvent))
			.thenReturn(expectedId);

		// When
		GitPushEventResponseDto handle = gitWebhookHandler.handle(gitMergeEventRequestDto);

		// Then
		assertAll("깃 웹훅 핸들링 검증",
			() -> assertEquals(expectedId, handle.projectId(), "프로젝트 ID가 예상과 다릅니다."),
			() -> verify(labelValidator, times(1)).validate(gitMergeEventRequestDto.labels()), // 검증 호출 확인
			() -> verify(gitWebHookAdapter, times(1)).toMergeRequestEvent(gitMergeEventRequestDto),
			() -> verify(gitMergeRequestManager, times(1)).register(gitMergeRequestEvent)
		);
	}

	@DisplayName("라벨 검증 실패 시 예외를 발생시킨다.")
	@Test
	void shouldThrowExceptionWhenLabelValidationFails() {
		// Given
		GitMergeEventRequestDto gitMergeEventRequestDto = mock(GitMergeEventRequestDto.class);

		when(labelValidator.validate(gitMergeEventRequestDto.labels()))
			.thenReturn(new LabelValidationResult(List.of("invalid-label")));

		// When & Then
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> gitWebhookHandler.handle(gitMergeEventRequestDto),
			"유효하지 않은 라벨로 인해 예외가 발생해야 합니다."
		);

		assertEquals("Invalid labels: [invalid-label]", exception.getMessage());
		verify(labelValidator, times(1)).validate(gitMergeEventRequestDto.labels());
		verifyNoInteractions(gitWebHookAdapter, gitMergeRequestManager);
	}



}
