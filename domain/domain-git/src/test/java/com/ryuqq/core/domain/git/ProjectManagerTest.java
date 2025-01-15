package com.ryuqq.core.domain.git;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProjectManagerTest extends BaseUnitTest {

	@InjectMocks
	private ProjectManager projectManager;

	@Mock
	private ProjectFinder projectFinder;

	@Mock
	private ProjectRegister projectRegister;

	@DisplayName("새로운 프로젝트는 저장되고 반환된 ID를 확인한다.")
	@Test
	void shouldSaveAndReturnProjectIdWhenNewProjectProvided() {
		// Given
		Project project = new Project(0L, GitType.GIT_LAB, "New Project", "repo-url", "owner", "Test Project");
		when(projectFinder.findByGitProjectId(eq(project.getGitProjectId()))).thenReturn(Optional.empty());
		when(projectRegister.register(eq(project))).thenReturn(123L);

		// When
		long result = projectManager.findOrRegisterProject(project);

		// Then
		assertEquals(123L, result, "새로운 프로젝트의 ID가 반환되어야 합니다.");
		verify(projectRegister, times(1)).register(eq(project));
	}

	@DisplayName("기존에 존재하는 프로젝트는 ID를 반환한다.")
	@Test
	void shouldReturnExistingProjectIdWhenProjectExists() {
		// Given
		Project existingProject = new Project(1L, 1L, GitType.GIT_LAB, "Existing Project", "repo-url", "owner", "Test Project");
		when(projectFinder.findByGitProjectId(eq(existingProject.getGitProjectId()))).thenReturn(Optional.of(existingProject));

		// When
		long result = projectManager.findOrRegisterProject(existingProject);

		// Then
		assertEquals(1L, result, "기존 프로젝트의 ID가 반환되어야 합니다.");
		verify(projectRegister, never()).register(any(Project.class));
	}

	@DisplayName("프로젝트 등록 시 정확한 프로젝트 정보가 전달된다.")
	@Test
	void shouldPassCorrectProjectToRegister() {
		// Given
		Project newProject = new Project(0L, GitType.GIT_LAB, "Correct Project", "repo-url", "owner", "Test Project");
		ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
		when(projectFinder.findByGitProjectId(eq(newProject.getGitProjectId()))).thenReturn(Optional.empty());

		// When
		projectManager.findOrRegisterProject(newProject);

		// Then
		verify(projectRegister, times(1)).register(projectCaptor.capture());
		Project capturedProject = projectCaptor.getValue();
		assertEquals("Correct Project", capturedProject.getName(), "프로젝트 이름이 올바르게 전달되어야 합니다.");
		assertEquals("repo-url", capturedProject.getRepositoryUrl(), "레포지토리 URL이 올바르게 전달되어야 합니다.");
		assertEquals("owner", capturedProject.getOwner(), "소유자가 올바르게 전달되어야 합니다.");
	}

}
