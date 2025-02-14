package com.ryuqq.core.domain.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.unit.test.BaseUnitTest;

class UpdateCheckerExecutorTest extends BaseUnitTest {

	@InjectMocks
	private UpdateCheckerExecutor updateCheckerExecutor;

	@Mock
	private UpdateCheckerProvider updateCheckerProvider;

	@Mock
	private UpdateChecker<Object, Object> mockUpdateChecker;

	@Mock
	private UpdateDecision mockUpdateDecision;

	@Test
	@DisplayName("executeChecker()가 적절한 UpdateChecker를 찾으면 checkUpdates()가 호출되어야 한다.")
	void shouldCallCheckUpdatesIfCheckerExists() {
		// Given
		Object existingField = new Object();
		Object updatedField = new Object();

		when(updateCheckerProvider.findChecker(existingField)).thenReturn(Optional.of(mockUpdateChecker));

		// When
		updateCheckerExecutor.executeChecker(mockUpdateDecision, existingField, updatedField);

		// Then
		verify(updateCheckerProvider).findChecker(existingField);
		verify(mockUpdateChecker).checkUpdates(mockUpdateDecision, existingField, updatedField);
	}

	@Test
	@DisplayName("executeChecker()가 UpdateChecker를 찾지 못하면 checkUpdates()가 호출되지 않아야 한다.")
	void shouldNotCallCheckUpdatesIfCheckerNotFound() {
		// Given
		Object existingField = new Object();
		Object updatedField = new Object();

		when(updateCheckerProvider.findChecker(existingField)).thenReturn(Optional.empty());

		// When
		updateCheckerExecutor.executeChecker(mockUpdateDecision, existingField, updatedField);

		// Then
		verify(updateCheckerProvider).findChecker(existingField);
		verify(mockUpdateChecker, never()).checkUpdates(any(), any(), any());
	}


}
