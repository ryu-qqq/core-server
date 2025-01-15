package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.storage.db.git.MergeReportCommand;

public class MergeReport {

	private Long id;
	private Long branchId;
	private int totalFiles;
	private int reviewedFiles;
	private int testPassedFiles;
	private int failedFiles;
	private double coverage;
	private MergeStatus mergeStatus;
	private String reviewer;

	public MergeReport(Long branchId, int totalFiles, int reviewedFiles, int testPassedFiles, int failedFiles,
					   double coverage, MergeStatus mergeStatus, String reviewer) {
		this.branchId = branchId;
		this.totalFiles = totalFiles;
		this.reviewedFiles = reviewedFiles;
		this.testPassedFiles = testPassedFiles;
		this.failedFiles = failedFiles;
		this.coverage = coverage;
		this.mergeStatus = mergeStatus;
		this.reviewer = reviewer;
	}

	public MergeReport(Long id, Long branchId, int totalFiles, int reviewedFiles, int testPassedFiles, int failedFiles,
					   double coverage, MergeStatus mergeStatus, String reviewer) {
		this.id = id;
		this.branchId = branchId;
		this.totalFiles = totalFiles;
		this.reviewedFiles = reviewedFiles;
		this.testPassedFiles = testPassedFiles;
		this.failedFiles = failedFiles;
		this.coverage = coverage;
		this.mergeStatus = mergeStatus;
		this.reviewer = reviewer;
	}

	public static MergeReport init(long branchId, int totalFiles){
		return new MergeReport(branchId, totalFiles, 0, 0, 0, 0, MergeStatus.OPENED, null);
	}

	public MergeReportCommand toCommand() {
		return new MergeReportCommand(id, branchId, totalFiles, reviewedFiles, testPassedFiles, failedFiles, coverage, mergeStatus, reviewer);
	}



}
