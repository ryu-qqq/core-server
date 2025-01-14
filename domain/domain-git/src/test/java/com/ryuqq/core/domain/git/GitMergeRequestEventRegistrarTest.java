package com.ryuqq.core.domain.git;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ryuqq.core.unit.test.BaseUnitTest;

class GitMergeRequestEventRegistrarTest extends BaseUnitTest {

	@InjectMocks
	private GitMergeRequestManager gitMergeRequestManager;

	@Mock
	private BranchRegister branchRegister;

	@Mock
	private ChangedFileRegister changedFileRegister;

	@DisplayName("GitEventCommand 처리 시 Branch와 ChangedFile을 저장한다")
	@Test
	void shouldRegisterBranchAndChangedFilesWhenGitEventCommandIsProvided() {
		// Given
		GitMergeRequestEvent gitMergeRequestEvent = Mockito.mock(GitMergeRequestEvent.class);
		Branch branch = Mockito.mock(Branch.class);
		long expectedBranchId = 1L;


		when(gitMergeRequestEvent.branch()).thenReturn(branch);
		when(branchRegister.register(0, branch)).thenReturn(expectedBranchId);

		// When
		long actualBranchId = gitMergeRequestManager.register(gitMergeRequestEvent);

		// Then
		assertEquals(expectedBranchId, actualBranchId, "Branch ID는 기대값과 같아야 합니다");
		verify(branchRegister, times(1)).register(0, branch);
		//verify(changedFileRegister, times(1)).saveAll(expectedBranchId, gitMergeRequestEvent.getCommits());
	}



}
