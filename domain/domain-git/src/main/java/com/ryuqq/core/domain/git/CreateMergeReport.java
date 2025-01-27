package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.MergeStatus;

public record CreateMergeReport(
	Long branchId,
	int totalFiles,
	int reviewedFiles,
	int testPassedFiles,
	int failedFiles,
	double coverage,
	MergeStatus mergeStatus,
	String reviewer
) implements MergeReportCommand {
	@Override
	public Long id() {
		return null;
	}
}
