package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.MergeStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "MERGE_REPORT")
public class MergeReportEntity extends BaseEntity {

	@Column(name = "BRANCH_ID", nullable = false)
	private Long branchId;

	@Column(name = "TOTAL_FILES", nullable = false)
	private int totalFiles;

	@Column(name = "REVIEWED_FILES", nullable = false)
	private int reviewedFiles;

	@Column(name = "TEST_PASSED_FILES", nullable = false)
	private int testPassedFiles;

	@Column(name = "FAILED_FILES", nullable = false)
	private int failedFiles;

	@Column(name = "COVERAGE", nullable = false)
	private double coverage;

	@Enumerated(EnumType.STRING)
	@Column(name = "MERGE_STATUS", nullable = false)
	private MergeStatus mergeStatus;

	@Column(name = "REVIEWER", nullable = false, length = 255)
	private String reviewer;

	protected MergeReportEntity() {}

	public MergeReportEntity(Long branchId, int totalFiles, int reviewedFiles, int testPassedFiles, int failedFiles,
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

	public MergeReportEntity(long id, Long branchId, int totalFiles, int reviewedFiles, int testPassedFiles, int failedFiles,
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

	public Long getBranchId() {
		return branchId;
	}

	public int getTotalFiles() {
		return totalFiles;
	}

	public int getReviewedFiles() {
		return reviewedFiles;
	}

	public int getTestPassedFiles() {
		return testPassedFiles;
	}

	public int getFailedFiles() {
		return failedFiles;
	}

	public double getCoverage() {
		return coverage;
	}

	public MergeStatus getMergeStatus() {
		return mergeStatus;
	}

	public String getReviewer() {
		return reviewer;
	}
}
