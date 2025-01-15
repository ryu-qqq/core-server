package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.ChangedFileQueryRepository;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ChangedFileFinder {

	private final ChangedFileMapper changedFileMapper;
	private final ChangedFileQueryRepository changedFileQueryRepository;

	public ChangedFileFinder(ChangedFileMapper changedFileMapper, ChangedFileQueryRepository changedFileQueryRepository) {
		this.changedFileMapper = changedFileMapper;
		this.changedFileQueryRepository = changedFileQueryRepository;
	}

	public List<ChangedFile> fetchByFilter(ChangedFileRequestFilter filter){
		return changedFileQueryRepository.fetchByFilter(filter.toStorageFilterDto())
			.stream()
			.map(changedFileMapper::toDomain)
			.toList();
	}

	public long countByFilter(ChangedFileRequestFilter filter){
		return changedFileQueryRepository.countByFilter(filter.toStorageFilterDto());
	}

}
