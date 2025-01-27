package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.MergeStatus;

public interface MergeReportCommand {

	Long id();
	Long branchId();
	int totalFiles();
	int reviewedFiles();
	int testPassedFiles();
	int failedFiles();
	double coverage();
	MergeStatus mergeStatus();
	String reviewer();
}
