package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.ChangedFileCommand;
import com.ryuqq.core.storage.db.git.ChangedFilePersistenceRepository;

@Component
public class ChangedFileRegister {

	private final ChangedFilePersistenceRepository changedFilePersistenceRepository;

	public ChangedFileRegister(ChangedFilePersistenceRepository changedFilePersistenceRepository) {
		this.changedFilePersistenceRepository = changedFilePersistenceRepository;
	}

	public void saveAll(long branchId, List<ChangedFile> changedFiles) {

		List<ChangedFileCommand> changedFileCommands = changedFiles.stream()
			.map(changedFile -> changedFile.toCommand(branchId))
			.toList();

		changedFilePersistenceRepository.saveAll(changedFileCommands);
	}

}
