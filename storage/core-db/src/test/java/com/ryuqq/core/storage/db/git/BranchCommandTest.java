package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.unit.test.BaseUnitTest;
import com.ryuqq.support.utils.EasyRandomUtils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BranchCommandTest extends BaseUnitTest {

	@Nested
	@DisplayName("toEntity Method Tests")
	class ToEntityTests {

		private final BranchCommand branchCommandWithId = EasyRandomUtils.getRandom(BranchCommand.class);
		private final BranchCommand branchCommandWithoutId = new BranchCommand(
			null,
			branchCommandWithId.projectId(),
			branchCommandWithId.repositoryName(),
			branchCommandWithId.repositoryUrl(),
			branchCommandWithId.name(),
			branchCommandWithId.baseBranch()
		);

		@Test
		@DisplayName("should convert BranchCommand with ID to BranchEntity")
		void shouldConvertBranchCommandWithIdToBranchEntity() {
			// When
			BranchEntity entity = branchCommandWithId.toEntity();

			// Then
			assertNotNull(entity, "BranchEntity는 null이 아니어야 합니다.");
			assertEquals(branchCommandWithId.id(), entity.getId(), "ID는 BranchCommand와 일치해야 합니다.");
			assertEquals(branchCommandWithId.projectId(), entity.getProjectId(), "Project ID는 일치해야 합니다.");
			assertEquals(branchCommandWithId.repositoryName(), entity.getRepositoryName(), "Repository Name은 일치해야 합니다.");
			assertEquals(branchCommandWithId.repositoryUrl(), entity.getRepositoryUrl(), "Repository URL은 일치해야 합니다.");
			assertEquals(branchCommandWithId.name(), entity.getName(), "Name은 일치해야 합니다.");
			assertEquals(branchCommandWithId.baseBranch(), entity.getBaseBranch(), "Base Branch는 일치해야 합니다.");
		}

		@Test
		@DisplayName("should convert BranchCommand without ID to BranchEntity")
		void shouldConvertBranchCommandWithoutIdToBranchEntity() {
			// When
			BranchEntity entity = branchCommandWithoutId.toEntity();

			// Then
			assertNotNull(entity, "BranchEntity는 null이 아니어야 합니다.");
			assertNull(entity.getId(), "ID는 null이어야 합니다.");
			assertEquals(branchCommandWithoutId.projectId(), entity.getProjectId(), "Project ID는 일치해야 합니다.");
			assertEquals(branchCommandWithoutId.repositoryName(), entity.getRepositoryName(), "Repository Name은 일치해야 합니다.");
			assertEquals(branchCommandWithoutId.repositoryUrl(), entity.getRepositoryUrl(), "Repository URL은 일치해야 합니다.");
			assertEquals(branchCommandWithoutId.name(), entity.getName(), "Name은 일치해야 합니다.");
			assertEquals(branchCommandWithoutId.baseBranch(), entity.getBaseBranch(), "Base Branch는 일치해야 합니다.");
		}
	}

}
