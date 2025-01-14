package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "BRANCH_COVERAGE")
public class BranchCoverageEntity extends BaseEntity {

	@Column(name = "BRANCH_ID", nullable = false)
	private Long branchId;

	@Column(name = "TOTAL_COVERAGE", nullable = false)
	private double totalCoverage;

	@Column(name = "TESTED_FILES", nullable = false)
	private int testedFiles;

	@Column(name = "TOTAL_FILES", nullable = false)
	private int totalFiles;

	protected BranchCoverageEntity() {}

	public BranchCoverageEntity(Long branchId, double totalCoverage, int testedFiles, int totalFiles) {
		this.branchId = branchId;
		this.totalCoverage = totalCoverage;
		this.testedFiles = testedFiles;
		this.totalFiles = totalFiles;
	}

	public Long getBranchId() {
		return branchId;
	}

	public double getTotalCoverage() {
		return totalCoverage;
	}

	public int getTestedFiles() {
		return testedFiles;
	}

	public int getTotalFiles() {
		return totalFiles;
	}
}
