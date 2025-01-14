package com.ryuqq.core.domain.git;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.storage.db.git.ChangedFileCommand;
import com.ryuqq.core.storage.db.git.ChangedFilePersistenceRepository;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ChangedFileRegisterTest extends BaseUnitTest {


	@InjectMocks
	private ChangedFileRegister changedFileRegister;

	@Mock
	private ChangedFilePersistenceRepository changedFilePersistenceRepository;

	@DisplayName("ChangedFile 처리 시 ChangedFile을 저장한다")
	@Test
	void shouldRegisterChangedFileWhenChangedFileProvided(){
		long branchId = 123L;

		ChangedFile changedFile1 = mock(ChangedFile.class);
		ChangedFile changedFile2 = mock(ChangedFile.class);

		ChangedFileCommand command1 = mock(ChangedFileCommand.class);
		ChangedFileCommand command2 = mock(ChangedFileCommand.class);

		when(changedFile1.toCommand(branchId)).thenReturn(command1);
		when(changedFile2.toCommand(branchId)).thenReturn(command2);

		List<ChangedFile> changedFiles = List.of(changedFile1, changedFile2);
		List<ChangedFileCommand> expectedCommands = List.of(command1, command2);

		doNothing().when(changedFilePersistenceRepository).saveAll(expectedCommands);

		changedFileRegister.register(branchId, changedFiles);

		verify(changedFile1, times(1)).toCommand(branchId);
		verify(changedFile2, times(1)).toCommand(branchId);
		verify(changedFilePersistenceRepository, times(1)).saveAll(expectedCommands);
	}


}
