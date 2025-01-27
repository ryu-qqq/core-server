package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.MergeReportCommand;
import com.ryuqq.core.domain.git.MergeReportPersistenceRepository;

@Repository
public class MergeReportJpaPersistenceRepository implements MergeReportPersistenceRepository {

	private final MergeReportStorageMapper mergeReportStorageMapper;
	private final MergeReportJpaRepository mergeReportJpaRepository;

	public MergeReportJpaPersistenceRepository(MergeReportStorageMapper mergeReportStorageMapper,
											   MergeReportJpaRepository mergeReportJpaRepository) {
		this.mergeReportStorageMapper = mergeReportStorageMapper;
		this.mergeReportJpaRepository = mergeReportJpaRepository;
	}

	@Override
	public void save(MergeReportCommand mergeReportCommand) {
		mergeReportJpaRepository.save(mergeReportStorageMapper.toEntity(mergeReportCommand));
	}

}
