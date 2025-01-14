package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.MergeStatus;

public record MergeReportCommand(
	Long id,
	Long branchId,
	int totalFiles,
	int reviewedFiles,
	int testPassedFiles,
	int failedFiles,
	double coverage,
	MergeStatus mergeStatus,
	String reviewer
) {

	public MergeReportEntity toEntity(){
		if(id != null){
			return new MergeReportEntity(id, branchId, totalFiles, reviewedFiles, testPassedFiles, failedFiles, coverage, mergeStatus, reviewer);
		}
		return new MergeReportEntity(branchId, totalFiles, reviewedFiles, testPassedFiles, failedFiles, coverage, mergeStatus, reviewer);
	}

}
