package com.ryuqq.core.storage.db.git;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ChangedFileJpaPersistenceRepository implements ChangedFilePersistenceRepository{

	private final ChangedFileJpaRepository changedFileJpaRepository;

	public ChangedFileJpaPersistenceRepository(ChangedFileJpaRepository changedFileJpaRepository) {
		this.changedFileJpaRepository = changedFileJpaRepository;
	}

	@Override
	public void save(ChangedFileCommand changedFileCommand) {
		changedFileJpaRepository.save(changedFileCommand.toEntity());
	}

	@Override
	public void saveAll(List<ChangedFileCommand> changedFileCommands) {
		List<ChangedFileEntity> changedFileEntities = changedFileCommands.stream()
			.map(ChangedFileCommand::toEntity)
			.toList();

		changedFileJpaRepository.saveAll(changedFileEntities);
	}

}
