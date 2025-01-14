package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.ChangedFilePersistenceRepository;

@Component
public class ChangedFileRegister {

	private final ChangedFilePersistenceRepository changedFilePersistenceRepository;
	private final ReviewExecutionRegister reviewExecutionRegister;

	public ChangedFileRegister(ChangedFilePersistenceRepository changedFilePersistenceRepository,
							   ReviewExecutionRegister reviewExecutionRegister) {
		this.changedFilePersistenceRepository = changedFilePersistenceRepository;
		this.reviewExecutionRegister = reviewExecutionRegister;
	}

	public void register(long commitId, List<ChangedFile> changedFiles) {
		for (ChangedFile changedFile : changedFiles) {
			long changedFileId = changedFilePersistenceRepository.save(changedFile.toCommand(commitId));
			reviewExecutionRegister.register(ReviewExecution.init(commitId, changedFileId));
		}
	}

}
