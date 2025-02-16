package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.unit.test.BaseUnitTest;

class UpdateDecisionExecutorTest extends BaseUnitTest {

	@InjectMocks
	private UpdateDecisionExecutor updateDecisionExecutor;

	@Mock
	private UpdateProcessorProvider processorProvider;

	@Mock
	private UpdateProcessor<Object> mockUpdateProcessor;

	@Mock
	private UpdateDecision mockUpdateDecision;

	@Mock
	private UpdateDomain<Object> mockUpdateDomain;


	@Test
	@DisplayName("execute()가 UpdateDecision의 모든 업데이트를 실행해야 한다.")
	void shouldExecuteAllUpdates() {
		// Given
		when(mockUpdateDecision.getRealTimeUpdates()).thenReturn(List.of(mockUpdateDomain));
		when(mockUpdateDecision.getBatchUpdates()).thenReturn(List.of(mockUpdateDomain));
		when(mockUpdateDomain.domain()).thenReturn(new Object()); // 더미 객체
		when(processorProvider.findProcessor(any())).thenReturn(Optional.of(mockUpdateProcessor));

		// When
		updateDecisionExecutor.execute(mockUpdateDecision);

		// Then
		verify(processorProvider, times(2)).findProcessor(any());
		verify(mockUpdateProcessor, times(2)).processUpdate(any());
	}

	@Test
	@DisplayName("execute()가 적절한 UpdateProcessor를 찾지 못하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProcessorNotFound() {
		// Given
		when(mockUpdateDecision.getRealTimeUpdates()).thenReturn(List.of(mockUpdateDomain));
		when(mockUpdateDomain.domain()).thenReturn(new Object());
		when(processorProvider.findProcessor(any())).thenReturn(Optional.empty());

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () -> updateDecisionExecutor.execute(mockUpdateDecision));
		assertTrue(exception.getMessage().contains("No processor found for domain type"));

		verify(processorProvider, times(1)).findProcessor(any());
		verify(mockUpdateProcessor, never()).processUpdate(any());
	}

	@Test
	@DisplayName("execute()는 업데이트가 없는 경우 아무 작업도 수행하지 않아야 한다.")
	void shouldDoNothingIfNoUpdates() {
		// Given
		when(mockUpdateDecision.getRealTimeUpdates()).thenReturn(List.of());
		when(mockUpdateDecision.getBatchUpdates()).thenReturn(List.of());

		// When
		assertDoesNotThrow(() -> updateDecisionExecutor.execute(mockUpdateDecision));

		// Then
		verify(processorProvider, never()).findProcessor(any());
		verify(mockUpdateProcessor, never()).processUpdate(any());
	}



}
