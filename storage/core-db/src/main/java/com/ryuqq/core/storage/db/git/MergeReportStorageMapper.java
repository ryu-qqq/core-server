package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.MergeReportCommand;

@Component
public class MergeReportStorageMapper {

	public MergeReportEntity toEntity(MergeReportCommand mergeReportCommand){
		if(mergeReportCommand.id() != null){
			return new MergeReportEntity(
				mergeReportCommand.id(),
				mergeReportCommand.branchId(),
				mergeReportCommand.totalFiles(),
				mergeReportCommand.reviewedFiles(),
				mergeReportCommand.testPassedFiles(),
				mergeReportCommand.failedFiles(),
				mergeReportCommand.coverage(),
				mergeReportCommand.mergeStatus(),
				mergeReportCommand.reviewer()
			);
		}

		return new MergeReportEntity(
			mergeReportCommand.branchId(),
			mergeReportCommand.totalFiles(),
			mergeReportCommand.reviewedFiles(),
			mergeReportCommand.testPassedFiles(),
			mergeReportCommand.failedFiles(),
			mergeReportCommand.coverage(),
			mergeReportCommand.mergeStatus(),
			mergeReportCommand.reviewer()
		);
	}
}
