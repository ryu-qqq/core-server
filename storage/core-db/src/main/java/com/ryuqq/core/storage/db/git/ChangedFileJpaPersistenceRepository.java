package com.ryuqq.core.storage.db.git;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.ChangedFileCommand;
import com.ryuqq.core.domain.git.ChangedFilePersistenceRepository;

@Repository
public class ChangedFileJpaPersistenceRepository implements ChangedFilePersistenceRepository {

	private final ChangedFileStorageMapper changedFileStorageMapper;
	private final ChangedFileJpaRepository changedFileJpaRepository;

	public ChangedFileJpaPersistenceRepository(ChangedFileStorageMapper changedFileStorageMapper,
											   ChangedFileJpaRepository changedFileJpaRepository) {
		this.changedFileStorageMapper = changedFileStorageMapper;
		this.changedFileJpaRepository = changedFileJpaRepository;
	}

	@Override
	public void save(ChangedFileCommand changedFileCommand) {
		changedFileJpaRepository.save(changedFileStorageMapper.toEntity(changedFileCommand));
	}

	@Override
	public void saveAll(List<ChangedFileCommand> changedFileCommand) {
		List<ChangedFileEntity> changedFileEntities = changedFileCommand.stream()
			.map(changedFileStorageMapper::toEntity)
			.toList();

		changedFileJpaRepository.saveAll(changedFileEntities);
	}

}
