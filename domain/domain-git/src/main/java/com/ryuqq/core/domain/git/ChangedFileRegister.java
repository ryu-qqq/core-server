package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.ChangedFilePersistenceRepository;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ChangedFileRegister {

	private final ChangedFilePersistenceRepository changedFilePersistenceRepository;

	public ChangedFileRegister(ChangedFilePersistenceRepository changedFilePersistenceRepository) {
		this.changedFilePersistenceRepository = changedFilePersistenceRepository;
	}

	public void register(long commitId, List<ChangedFile> changedFiles) {
		for (ChangedFile changedFile : changedFiles) {
			changedFilePersistenceRepository.save(changedFile.toCommand(commitId));
		}
	}

}
