package com.ryuqq.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.BranchRegister;
import com.ryuqq.core.domain.git.ChangedFileRegister;
import com.ryuqq.core.domain.git.GitEvent;
import com.ryuqq.core.domain.git.GitEventRegistrar;
import com.ryuqq.core.unit.test.BaseUnitTest;

class GitEventRegistrarTest extends BaseUnitTest {

	@InjectMocks
	private GitEventRegistrar gitEventRegistrar;

	@Mock
	private BranchRegister branchRegister;

	@Mock
	private ChangedFileRegister changedFileRegister;

	@DisplayName("GitEventCommand 처리 시 Branch와 ChangedFile을 저장한다")
	@Test
	void shouldRegisterBranchAndChangedFilesWhenGitEventCommandIsProvided() {
		// Given
		GitEvent gitEvent = mock(GitEvent.class);
		Branch branch = mock(Branch.class);
		long expectedBranchId = 1L;


		when(gitEvent.getBranch()).thenReturn(branch);
		when(branchRegister.register(branch)).thenReturn(expectedBranchId);

		// When
		long actualBranchId = gitEventRegistrar.register(gitEvent);

		// Then
		assertEquals(expectedBranchId, actualBranchId, "Branch ID는 기대값과 같아야 합니다");
		verify(branchRegister, times(1)).register(branch);
		verify(changedFileRegister, times(1)).saveAll(expectedBranchId, gitEvent.getChangedFiles());
	}



}
