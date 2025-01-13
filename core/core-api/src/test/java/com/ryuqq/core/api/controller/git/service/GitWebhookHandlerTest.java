package com.ryuqq.core.api.controller.git.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.api.controller.git.mapper.GitWebHookAdapter;
import com.ryuqq.core.api.controller.git.request.GitPushEventRequestDto;
import com.ryuqq.core.api.controller.git.response.GitPushEventResponseDto;
import com.ryuqq.core.domain.GitEvent;
import com.ryuqq.core.domain.GitEventRegistrar;
import com.ryuqq.core.unit.test.BaseUnitTest;

class GitWebhookHandlerTest extends BaseUnitTest {

	@InjectMocks
	private GitWebhookHandler gitWebhookHandler;

	@Mock
	GitEventRegistrar gitEventRegistrar;

	@Mock
	GitWebHookAdapter gitWebHookAdapter;


	@DisplayName("깃 웹훅을 핸들링 한다.")
	@Test
	void shouldHandleWhenGitWebHookTrigger(){
		long expectedId = 1L;

		GitPushEventRequestDto gitPushEventRequestDto = mock(GitPushEventRequestDto.class);
		GitEvent gitEvent = mock(GitEvent.class);

		when(gitWebHookAdapter.toDomain(gitPushEventRequestDto)).thenReturn(gitEvent);
		when(gitEventRegistrar.register(gitEvent)).thenReturn(expectedId);

		GitPushEventResponseDto handle = gitWebhookHandler.handle(gitPushEventRequestDto);

		assertAll("깃 웹훅을 핸들링 검증",
			() -> String.format("예상된 브랜치 ID [%d]와 실제 반환된 값 [%d]가 다릅니다.", expectedId, handle.branchId()),
			()->  verify(gitEventRegistrar, times(1)).register(gitEvent),
			()->  verify(gitWebHookAdapter, times(1)).toDomain(gitPushEventRequestDto)
		);

	}


}
