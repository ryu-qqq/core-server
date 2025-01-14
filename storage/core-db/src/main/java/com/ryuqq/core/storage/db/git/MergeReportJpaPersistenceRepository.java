package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Repository;

@Repository
public class MergeReportJpaPersistenceRepository implements MergeReportPersistenceRepository{

	private final MergeReportJpaRepository mergeReportJpaRepository;

	public MergeReportJpaPersistenceRepository(MergeReportJpaRepository mergeReportJpaRepository) {
		this.mergeReportJpaRepository = mergeReportJpaRepository;
	}

	@Override
	public void save(MergeReportCommand mergeReportCommand) {
		mergeReportJpaRepository.save(mergeReportCommand.toEntity());
	}

}
