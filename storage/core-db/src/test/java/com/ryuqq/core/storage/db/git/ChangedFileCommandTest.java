package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.unit.test.BaseUnitTest;
import com.ryuqq.support.utils.EasyRandomUtils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangedFileCommandTest extends BaseUnitTest {

	@Nested
	@DisplayName("toEntity Method Tests")
	class ToEntityTests {

		private final ChangedFileCommand changedFileCommandWithId = EasyRandomUtils.getRandom(ChangedFileCommand.class);
		private final ChangedFileCommand changedFileCommandWithoutId = new ChangedFileCommand(
			null,
			changedFileCommandWithId.branchId(),
			changedFileCommandWithId.className(),
			changedFileCommandWithId.filePath(),
			changedFileCommandWithId.changeType(),
			changedFileCommandWithId.status(),
			changedFileCommandWithId.commitId(),
			changedFileCommandWithId.commitMessage()
		);

		@Test
		@DisplayName("should convert ChangedFileCommand with ID to ChangedFileEntity")
		void shouldConvertChangedFileCommandWithIdToChangedFileEntity() {
			// When
			ChangedFileEntity entity = changedFileCommandWithId.toEntity();

			// Then
			assertNotNull(entity, "ChangedFileEntity는 null이 아니어야 합니다.");
			assertEquals(changedFileCommandWithId.id(), entity.getId(), "ID는 ChangedFileCommand와 일치해야 합니다.");
			assertEquals(changedFileCommandWithId.branchId(), entity.getBranchId(), "Branch ID는 일치해야 합니다.");
			assertEquals(changedFileCommandWithId.className(), entity.getClassName(), "Class Name은 일치해야 합니다.");
			assertEquals(changedFileCommandWithId.filePath(), entity.getFilePath(), "File Path는 일치해야 합니다.");
			assertEquals(changedFileCommandWithId.changeType(), entity.getChangeType(), "Change Type은 일치해야 합니다.");
			assertEquals(changedFileCommandWithId.status(), entity.getStatus(), "Status는 일치해야 합니다.");
			assertEquals(changedFileCommandWithId.commitId(), entity.getCommitId(), "Commit ID는 일치해야 합니다.");
			assertEquals(changedFileCommandWithId.commitMessage(), entity.getCommitMessage(), "Commit Message는 일치해야 합니다.");
		}

		@Test
		@DisplayName("should convert ChangedFileCommand without ID to ChangedFileEntity")
		void shouldConvertChangedFileCommandWithoutIdToChangedFileEntity() {
			// When
			ChangedFileEntity entity = changedFileCommandWithoutId.toEntity();

			// Then
			assertNotNull(entity, "ChangedFileEntity는 null이 아니어야 합니다.");
			assertNull(entity.getId(), "ID는 null이어야 합니다.");
			assertEquals(changedFileCommandWithoutId.branchId(), entity.getBranchId(), "Branch ID는 일치해야 합니다.");
			assertEquals(changedFileCommandWithoutId.className(), entity.getClassName(), "Class Name은 일치해야 합니다.");
			assertEquals(changedFileCommandWithoutId.filePath(), entity.getFilePath(), "File Path는 일치해야 합니다.");
			assertEquals(changedFileCommandWithoutId.changeType(), entity.getChangeType(), "Change Type은 일치해야 합니다.");
			assertEquals(changedFileCommandWithoutId.status(), entity.getStatus(), "Status는 일치해야 합니다.");
			assertEquals(changedFileCommandWithoutId.commitId(), entity.getCommitId(), "Commit ID는 일치해야 합니다.");
			assertEquals(changedFileCommandWithoutId.commitMessage(), entity.getCommitMessage(), "Commit Message는 일치해야 합니다.");
		}
	}


}
