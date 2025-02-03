package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

@Component
public class MergeReportRegister {

	private final MergeReportPersistenceRepository mergeReportPersistenceRepository;

	public MergeReportRegister(MergeReportPersistenceRepository mergeReportPersistenceRepository) {
		this.mergeReportPersistenceRepository = mergeReportPersistenceRepository;
	}

	public void register(MergeReport mergeReport) {
		mergeReportPersistenceRepository.save(mergeReport.toCommand());
	}

}
