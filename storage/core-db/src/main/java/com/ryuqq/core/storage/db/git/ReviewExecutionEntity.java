package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "REVIEW_EXECUTION")
public class ReviewExecutionEntity extends BaseEntity {

	@Column(name = "COMMIT_ID", nullable = false)
	private Long commitId;

	@Column(name = "CHANGED_FILE_ID", nullable = false)
	private Long changedFileId;

	@Enumerated(EnumType.STRING)
	@Column(name = "REVIEW_STATUS", nullable = false, length = 20)
	private ReviewStatus reviewStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "TEST_STATUS", nullable = false, length = 20)
	private TestStatus testStatus;

	@Column(name = "RESULT_PATH", nullable = true, length = 255)
	private String resultPath;



	protected ReviewExecutionEntity() {}

	public ReviewExecutionEntity(Long commitId, Long changedFileId, TestStatus testStatus, ReviewStatus reviewStatus) {
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.testStatus = testStatus;
		this.reviewStatus = reviewStatus;
	}

	public ReviewExecutionEntity(long id, Long commitId, Long changedFileId, ReviewStatus reviewStatus, TestStatus testStatus,
								 String resultPath) {
		this.id = id;
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.reviewStatus = reviewStatus;
		this.testStatus = testStatus;
		this.resultPath = resultPath;
	}

	public Long getCommitId() {
		return commitId;
	}

	public Long getChangedFileId() {
		return changedFileId;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public TestStatus getTestStatus() {
		return testStatus;
	}

	public String getResultPath() {
		return resultPath;
	}
}
