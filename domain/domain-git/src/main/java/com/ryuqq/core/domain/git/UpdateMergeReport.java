package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.MergeStatus;

public record UpdateMergeReport(
	Long id,
	Long branchId,
	int totalFiles,
	int reviewedFiles,
	int testPassedFiles,
	int failedFiles,
	double coverage,
	MergeStatus mergeStatus,
	String reviewer
) implements MergeReportCommand {
}
