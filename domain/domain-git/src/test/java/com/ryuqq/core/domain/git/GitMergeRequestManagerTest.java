package com.ryuqq.core.domain.git;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ryuqq.core.unit.test.BaseUnitTest;

class GitMergeRequestManagerTest extends BaseUnitTest {

	@InjectMocks
	private GitMergeRequestManager gitMergeRequestManager;

	@Mock
	private ProjectManager projectManager;

	@Mock
	private BranchManager branchManager;

	@Mock
	private MergeReportRegister mergeReportRegister;


	@DisplayName("기존 프로젝트가 존재할 때 register 메서드를 검증한다.")
	@Test
	void shouldRegisterWhenProjectExistsUsingMatchers() {
		// Given
		GitMergeRequestEvent event = Mockito.mock(GitMergeRequestEvent.class);

		when(event.project()).thenReturn(Mockito.mock(Project.class));
		when(event.branch()).thenReturn(Mockito.mock(Branch.class));
		when(event.commits()).thenReturn(List.of(Mockito.mock(Commit.class)));
		when(event.getTotalFilesCount()).thenReturn(5);

		when(projectManager.findOrRegisterProject(any(Project.class))).thenReturn(1L);
		when(branchManager.registerBranchWithCommits(anyLong(), any(Branch.class), anyList())).thenReturn(2L);

		// When
		long result = gitMergeRequestManager.register(event);

		// Then
		assertEquals(1L, result, "프로젝트 ID가 반환되어야 합니다.");
		verify(projectManager, times(1)).findOrRegisterProject(any(Project.class));
		verify(branchManager, times(1)).registerBranchWithCommits(eq(1L), any(Branch.class), anyList());
		verify(mergeReportRegister, times(1)).register(any(MergeReport.class));
	}

}
