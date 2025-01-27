package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.MergeStatus;

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

	private MergeReport(Long id, Long branchId, int totalFiles, int reviewedFiles, int testPassedFiles, int failedFiles,
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


	public MergeReportCommand toCommand() {
		if(id != null){
			return new UpdateMergeReport(id, branchId, totalFiles, reviewedFiles, testPassedFiles, failedFiles, coverage, mergeStatus, reviewer);
		}
		return new CreateMergeReport(branchId, totalFiles, reviewedFiles, testPassedFiles, failedFiles, coverage, mergeStatus, reviewer);
	}



}
